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
        super(new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator()), "King", color);
    }

    public King(Color color, Space position) {
        super("King", color, new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator()), position);
    }

    public King(String name, Color color, Space position) {
        super(name, color, new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator()), position);
    }

    @Override
    protected void computeCaptureZone() {
        captureZone = captureZoneGenerator.generate(this.position, this.chessBoard.getHeight(),
                this.chessBoard.getWidth(), 1).collect(Collectors.toSet());
    }
}
