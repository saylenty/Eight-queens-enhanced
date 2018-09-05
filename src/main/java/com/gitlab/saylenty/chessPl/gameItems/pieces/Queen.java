/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;

public class Queen extends Piece {

    public Queen(Color color) {
        super("Queen", color);
    }

    public Queen(Color color, Space position) {
        super("Queen", color, position);
    }

    public Queen(String name, Color color, Space position) {
        super(name, color, position);
    }

    @Override
    protected void computeCaptureZone() {
        up();
        down();
        left();
        right();
        upLeftDiagonal();
        downRightDiagonal();
        upRightDiagonal();
        bottomLeftDiagonal();
    }
}
