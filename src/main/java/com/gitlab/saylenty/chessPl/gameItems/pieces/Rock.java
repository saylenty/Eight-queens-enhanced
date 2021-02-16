/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

public class Rock extends Piece {

    public Rock(Color color) {
        super("Rock", color);
    }

    public Rock(Color color, BoardSquare position) {
        super("Rock", color, position);
    }

    public Rock(String name, Color color, BoardSquare position) {
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
