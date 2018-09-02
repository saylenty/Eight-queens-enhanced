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
    @Getter
    private final Set<Piece> boardPieces;

    /**
     * All positions (coordinates) that the figures could move to
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
     * Calculates all free (that is not in any figure danger zone) board spaces
     *
     * @return all spaces for this chessBoard instance
     */
    public Set<Space> getFreeSpaces() {
        Set<Space> piecesCaptureZone = boardPieces.stream()
                .flatMap(piece -> piece.getCaptureZone().stream())
                .collect(Collectors.toSet());
        return Sets.difference(this.getBoardSpaces(), piecesCaptureZone);
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
