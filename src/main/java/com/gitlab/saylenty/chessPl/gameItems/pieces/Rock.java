/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiStraightLineGenerator;

public class Rock extends Piece {

    public Rock(Color color) {
        super(createCaptureZoneGenerator(), "Rock", color);
    }

    public Rock(Color color, Space position) {
        super("Rock", color, createCaptureZoneGenerator(), position);
    }

    public Rock(String name, Color color, Space position) {
        super(name, color, createCaptureZoneGenerator(), position);
    }

    private static BiStraightLineGenerator createCaptureZoneGenerator() {
        return new BiStraightLineGenerator();
    }
}
