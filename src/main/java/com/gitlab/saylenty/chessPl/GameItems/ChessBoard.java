/*
  <p>
  Created by Saylenty on 11-Apr-17.
  Copyright (c) 2017
  </p>
 */
package com.gitlab.saylenty.chessPl.GameItems;

import com.gitlab.saylenty.chessPl.GameItems.Figures.Piece;
import com.gitlab.saylenty.chessPl.Infrustucture.Space;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class ChessBoard {
    private final int height;
    private final int width;
    private final Set<Piece> boardPieces;
    private final Set<Space> boardSpaces;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Set<Piece> getBoardPieces() {
        return boardPieces;
    }

    public ChessBoard(int height, int width) {
        this.width = width;
        this.height = height;
        boardPieces = new HashSet<>();
        boardSpaces = new HashSet<>();
    }

    /**
     * Calculates (if necessary) all spaces that chessBoard contains
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
     * @return all spaces for this chessBoard instance
     */
    public Set<Space> getFreeSpaces() {
        Set<Space> collect = new HashSet<>((int) (width * height * .75));
        boardPieces.stream().map(Piece::getCaptureZone).forEach(collect::addAll);
        return Sets.difference(this.getBoardSpaces(), collect);
    }

    /**
     * Associates the board with piece
     * @param piece a piece to associates with
     * @return whether association were successful
     */
    public boolean addPiece(Piece piece) {
        boolean res = boardPieces.add(piece);
        if (res){
            piece.setBoard(this);
        }
        return res;
    }

    /**
     * removes piece from the board
     * @param piece a piece for removing
     * @return whether removing were successful
     */
    public boolean removePiece(Piece piece) {
        return boardPieces.remove(piece);
    }
}
