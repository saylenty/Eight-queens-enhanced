/*
  <p>
  Created by Saylenty on 11-Apr-17.
  Copyright (c) 2017
  </p>
 */
package com.gitlab.saylenty.chessPl.gameItems;

import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoard {

    /**
     * The height of the board
     */
    @Getter
    private final int height;

    /**
     * The width of the board
     */
    @Getter
    private final int width;

    /**
     * Associated figures
     */
    private final Set<Piece> boardPieces;

    public final Stream<? extends Piece> getBoardPieces() {
        return boardPieces.stream();
    }

    /**
     * All positions (coordinates) that the figures could move to on this board instance
     */
    private final Map<Integer, Map<Integer, BoardSquare>> boardSquares;

    public ChessBoard(int height, int width) {
        this.width = width;
        this.height = height;
        boardPieces = new HashSet<>();
        boardSquares = new HashMap<>(width, 1);
    }

    private void initBoardSquares(int height, int width) {
        for (int i = 0; i < width; i++) {
            HashMap<Integer, BoardSquare> iValue = new HashMap<>(height, 1);
            for (int j = 0; j < height; j++) {
                iValue.put(j, new BoardSquare(i, j));
            }
            boardSquares.put(i, iValue);
        }
    }

    /**
     * Calculates (if necessary) all spaces that chessBoard contains
     *
     * @return all spaces for this chessBoard instance
     */
    public Set<BoardSquare> getBoardSquares() {
        if (boardSquares.isEmpty()) {
            initBoardSquares(height, width);
        }
        return boardSquares.values().stream()
                .flatMap(e -> e.values().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves aggregated danger zone for all figures
     *
     * @return spaces that are in any figure danger zone of this chessBoard instance
     */
    public Stream<BoardSquare> getCaptureZone() {
        return getBoardSquares().stream().filter(BoardSquare::isLocked);
    }

    /**
     * Retrieves free board spaces (that is not in any figure danger zone)
     *
     * @return free spaces for this chessBoard instance
     */
    public Stream<BoardSquare> getFreeZone() {
        return getBoardSquares().stream().filter(BoardSquare::isUnlocked);
    }

    /**
     * Associates the board with piece
     *
     * @param piece a piece to associates with
     * @return whether association was successful or not
     */
    public boolean add(Piece piece) {
        boolean isAdded = boardPieces.add(piece);
        for (BoardSquare space : piece.getCaptureZone()) {
            boardSquares.get(space.getX())
                    .get(space.getY())
                    .lock();
        }
        return isAdded;
    }

    /**
     * Removes piece from the board
     *
     * @param piece a piece for removing
     * @return whether removing was successful or not
     */
    public boolean remove(Piece piece) {
        boolean isRemoved = boardPieces.remove(piece);
        for (BoardSquare space : piece.getCaptureZone()) {
            boardSquares.get(space.getX())
                    .get(space.getY())
                    .unlock();
        }
        return isRemoved;
    }

    /**
     * Checks whether chessBoard has association with any figure
     *
     * @return true if this chessBoard contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return boardPieces.isEmpty();
    }
}
