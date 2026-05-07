/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiStraightLineGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;

public final class Rock extends Piece {

    private static final RangeGenerationStrategy GENERATOR = new BiStraightLineGenerator();

    public Rock(Color color) {
        super("Rock", color, GENERATOR);
    }

    public Rock(Color color, BoardSquare position) {
        super("Rock", color, GENERATOR, position);
    }

    public Rock(String name, Color color, BoardSquare position) {
        super(name, color, GENERATOR, position);
    }
}
