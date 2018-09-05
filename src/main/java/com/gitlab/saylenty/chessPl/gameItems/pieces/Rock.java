/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;

public class Rock extends Piece {

    public Rock(Color color) {
        super("Rock", color);
    }

    public Rock(Color color, Space position) {
        super("Rock", color, position);
    }

    public Rock(String name, Color color, Space position) {
        super(name, color, position);
    }

    @Override
    protected void computeCaptureZone() {
        up();
        down();
        left();
        right();
    }
}
