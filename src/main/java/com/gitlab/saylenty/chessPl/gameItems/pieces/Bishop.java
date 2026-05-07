/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiDiagonalGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;

public final class Bishop extends Piece {

    private static final RangeGenerationStrategy GENERATOR = new BiDiagonalGenerator();

    public Bishop(Color color) {
        super("Bishop", color, GENERATOR);
    }

    public Bishop(Color color, BoardSquare position) {
        super("Bishop", color, GENERATOR, position);
    }

    public Bishop(String name, Color color, BoardSquare position) {
        super(name, color, GENERATOR, position);
    }
}
