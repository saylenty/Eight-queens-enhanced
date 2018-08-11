/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Space;

import java.awt.*;

public class Bishop extends Piece {

    public Bishop() {
        this(null, new Space(0, 0));
    }

    public Bishop(Color color) {
        this(color, new Space(0, 0));
    }

    public Bishop(Color color, Space position) {
        this("Bishop", color, position);
    }

    public Bishop(String name, Color color, Space position) {
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
