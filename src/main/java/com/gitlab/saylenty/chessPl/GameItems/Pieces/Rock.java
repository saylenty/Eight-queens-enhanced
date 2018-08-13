/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Pieces;

import com.gitlab.saylenty.chessPl.Infrustucture.Space;

import java.awt.*;

public class Rock extends Piece {

    public Rock() {
        this(null, new Space(0, 0));
    }

    public Rock(Color color, Space position) {
        this("Rock", color, position);
    }

    public Rock(String name, Color color, Space position) {
        super(name, color, position);
    }

    public Rock(Color color) {
        this(color, new Space(0, 0));
    }

    @Override
    protected void computeCaptureZone() {
        up();
        down();
        left();
        right();
    }
}
