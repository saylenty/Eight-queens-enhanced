/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiDiagonalGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiStraightLineGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.CompositeGenerator;

import java.util.stream.Collectors;

public class King extends Piece {

    public King(Color color) {
        super(createCaptureZoneGenerator(), "King", color);
    }

    public King(Color color, Space position) {
        super("King", color, createCaptureZoneGenerator(), position);
    }

    public King(String name, Color color, Space position) {
        super(name, color, createCaptureZoneGenerator(), position);
    }

    private static CompositeGenerator createCaptureZoneGenerator() {
        return new CompositeGenerator(new BiDiagonalGenerator(1),
                new BiStraightLineGenerator(1));
    }

    @Override
    protected void computeCaptureZone() {
        captureZone = captureZoneGenerator.generate(this.position, this.chessBoard.getHeight(),
                this.chessBoard.getWidth()).collect(Collectors.toSet());
    }
}
