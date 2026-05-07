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

public final class King extends Piece {

    private static final RangeGenerationStrategy GENERATOR =
            new CompositeGenerator(new BiDiagonalGenerator(1), new BiStraightLineGenerator(1));

    public King(Color color) {
        super("King", color, GENERATOR);
    }

    public King(Color color, BoardSquare position) {
        super("King", color, GENERATOR, position);
    }

    public King(String name, Color color, BoardSquare position) {
        super(name, color, GENERATOR, position);
    }
}
