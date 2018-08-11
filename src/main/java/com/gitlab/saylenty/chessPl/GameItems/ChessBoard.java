/*
  <p>
  Created by Saylenty on 11-Apr-17.
  Copyright (c) 2017
  </p>
 */
package com.gitlab.saylenty.chessPl.GameItems;

import com.gitlab.saylenty.chessPl.GameItems.Figures.Piece;
import com.gitlab.saylenty.chessPl.Infrustucture.Space;

import java.util.HashSet;
import java.util.Set;

public class ChessBoard {
    private final int height;
    private final int width;
    private final Set<Piece> boardPieces;
    private final Set<Space> allBoardSpaces;

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
        allBoardSpaces = new HashSet<>();
    }

    public Set<Space> getAllBoardSpaces() {
        if (!allBoardSpaces.isEmpty()) {
            return allBoardSpaces;
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                allBoardSpaces.add(new Space(x, y));
            }
        }
        return allBoardSpaces;
    }

    public Set<Space> getFreeSpaces() {
        Set<Space> difference = new HashSet<>(this.getAllBoardSpaces());
        Set<Space> collect = new HashSet<>((int) (width * height * .75));
        boardPieces.forEach(f -> collect.addAll(f.getCaptureZone()));
        difference.removeAll(collect);
        return difference;
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
