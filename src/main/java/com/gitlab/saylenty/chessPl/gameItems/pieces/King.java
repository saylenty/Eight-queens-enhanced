/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;

public class King extends Piece {

    public King(Color color) {
        super("King", color);
    }

    public King(Color color, Space position) {
        super("King", color, position);
    }

    public King(String name, Color color, Space position) {
        super(name, color, position);
    }

    @Override
    protected void computeCaptureZone() {
        left(1);
        right(1);
        up(1);
        down(1);
        upLeftDiagonal(1);
        downRightDiagonal(1);
        upRightDiagonal(1);
        bottomLeftDiagonal(1);
    }
}
