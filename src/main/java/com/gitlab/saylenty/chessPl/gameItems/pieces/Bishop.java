/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiDiagonalGenerator;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(createCaptureZoneGenerator(), "Bishop", color);
    }

    public Bishop(Color color, Space position) {
        super("Bishop", color, createCaptureZoneGenerator(), position);
    }

    public Bishop(String name, Color color, Space position) {
        super(name, color, createCaptureZoneGenerator(), position);
    }

    private static BiDiagonalGenerator createCaptureZoneGenerator() {
        return new BiDiagonalGenerator();
    }
}
