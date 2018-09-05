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

import java.util.HashSet;
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
    private final Set<Space> boardSpaces;

    public ChessBoard(int height, int width) {
        this.width = width;
        this.height = height;
        boardPieces = new HashSet<>();
        boardSpaces = new HashSet<>(width * height, 1);
    }

    /**
     * Calculates (if necessary) all spaces that chessBoard contains
     *
     * @return all spaces for this chessBoard instance
     */
    public Set<Space> getBoardSpaces() {
        if (boardSpaces.isEmpty()) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    boardSpaces.add(new Space(x, y));
                }
            }
        }
        return boardSpaces;
    }

    /**
     * Retrieves aggregated danger zone for all figures
     *
     * @return spaces that are in any figure danger zone of this chessBoard instance
     */
    public Stream<Space> getCaptureZone() {
        return boardPieces.stream().flatMap(s -> s.getCaptureZone().stream());
    }

    /**
     * Retrieves free board spaces (that is not in any figure danger zone)
     *
     * @return free spaces for this chessBoard instance
     */
    public Stream<Space> getFreeZone() {
        Set<Space> piecesCaptureZone = getCaptureZone().collect(Collectors.toSet());
        return Sets.difference(this.getBoardSpaces(), piecesCaptureZone).stream();
    }

    /**
     * Associates the board with piece
     *
     * @param piece a piece to associates with
     * @return whether association was successful or not
     */
    public boolean add(Piece piece) {
        return boardPieces.add(piece);
    }

    /**
     * Removes piece from the board
     *
     * @param piece a piece for removing
     * @return whether removing was successful or not
     */
    public boolean remove(Piece piece) {
        return boardPieces.remove(piece);
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
