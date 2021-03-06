/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super("Bishop", color);
    }

    public Bishop(Color color, BoardSquare position) {
        super("Bishop", color, position);
    }

    public Bishop(String name, Color color, BoardSquare position) {
        super(name, color, position);
    }

    @Override
    protected void computeCaptureZone() {
        upLeftDiagonal();
        downRightDiagonal();
        upRightDiagonal();
        bottomLeftDiagonal();
    }
}
