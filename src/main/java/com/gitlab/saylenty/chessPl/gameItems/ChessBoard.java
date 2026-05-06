/*
  <p>
  Created by Saylenty on 11-Apr-17.
  Copyright (c) 2017
  </p>
 */
package com.gitlab.saylenty.chessPl.gameItems;

import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoard {

    private final int height;
    private final int width;
    private final Set<Piece> boardPieces;
    private final Map<Integer, Map<Integer, BoardSquare>> boardSquares;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public final Stream<? extends Piece> getBoardPieces() {
        return boardPieces.stream();
    }

    public ChessBoard(int height, int width) {
        this.width = width;
        this.height = height;
        boardPieces = new HashSet<>();
        boardSquares = new HashMap<>(width, 1);
    }

    private void initBoardSquares(int height, int width) {
        for (int i = 0; i < width; i++) {
            var iValue = new HashMap<Integer, BoardSquare>(height, 1);
            for (int j = 0; j < height; j++) {
                iValue.put(j, new BoardSquare(i, j));
            }
            boardSquares.put(i, iValue);
        }
    }

    public Set<BoardSquare> getBoardSquares() {
        if (boardSquares.isEmpty()) {
            initBoardSquares(height, width);
        }
        return boardSquares.values().stream()
                .flatMap(e -> e.values().stream())
                .collect(Collectors.toSet());
    }

    public Stream<BoardSquare> getCaptureZone() {
        return getBoardSquares().stream().filter(BoardSquare::isLocked);
    }

    public Stream<BoardSquare> getFreeZone() {
        return getBoardSquares().stream().filter(BoardSquare::isUnlocked);
    }

    public boolean add(Piece piece) {
        boolean isAdded = boardPieces.add(piece);
        for (BoardSquare space : piece.getCaptureZone()) {
            boardSquares.get(space.getX())
                    .get(space.getY())
                    .lock();
        }
        return isAdded;
    }

    public boolean remove(Piece piece) {
        return boardPieces.remove(piece) && clearCaptureZoneForPiece(piece);
    }

    private boolean clearCaptureZoneForPiece(Piece piece) {
        for (BoardSquare space : piece.getCaptureZone()) {
            boardSquares.get(space.getX())
                    .get(space.getY())
                    .unlock();
        }
        return true;
    }

    public boolean isEmpty() {
        return boardPieces.isEmpty();
    }
}
