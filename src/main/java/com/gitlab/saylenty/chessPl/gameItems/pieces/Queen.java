/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiDiagonalGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiStraightLineGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.CompositeGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;

public final class Queen extends Piece {

    private static final RangeGenerationStrategy GENERATOR =
            new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator());

    public Queen(Color color) {
        super("Queen", color, GENERATOR);
    }

    public Queen(Color color, BoardSquare position) {
        super("Queen", color, GENERATOR, position);
    }

    public Queen(String name, Color color, BoardSquare position) {
        super(name, color, GENERATOR, position);
    }
}
