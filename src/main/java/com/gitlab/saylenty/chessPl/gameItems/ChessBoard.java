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

public class ChessBoard {
    @Getter
    private final int height;

    @Getter
    private final int width;

    @Getter
    private final Set<Piece> boardPieces;

    private final Set<Space> boardSpaces;

    public ChessBoard(int height, int width) {
        this.width = width;
        this.height = height;
        boardPieces = new HashSet<>();
        boardSpaces = new HashSet<>();
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
        Set<Space> piecesCaptureZone = new HashSet<>((int) (width * height * .75));
        boardPieces.stream().map(Piece::getCaptureZone).forEach(piecesCaptureZone::addAll);
        return Sets.difference(this.getBoardSpaces(), piecesCaptureZone);
    }

    /**
     * Associates the board with piece
     *
     * @param piece a piece to associates with
     * @return whether association was successful or not
     */
    public boolean addPiece(Piece piece) {
        return boardPieces.add(piece);
    }

    /**
     * Removes piece from the board
     *
     * @param piece a piece for removing
     * @return whether removing was successful or not
     */
    public boolean removePiece(Piece piece) {
        return boardPieces.remove(piece);
    }
}
