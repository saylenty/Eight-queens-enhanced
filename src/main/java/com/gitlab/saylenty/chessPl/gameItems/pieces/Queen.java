/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.awt.*;

public class Queen extends Piece {

    public Queen() {
        this(null, new Space(0, 0));
    }

    public Queen(Color color, Space position) {
        this("Queen", color, position);
    }

    public Queen(String name, Color color, Space position) {
        super(name, color, position);
    }

    public Queen(Color color) {
        this(color, new Space(0, 0));
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
