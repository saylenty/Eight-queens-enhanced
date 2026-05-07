/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.generator.KnightGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;

public final class Knight extends Piece {

    private static final RangeGenerationStrategy GENERATOR = new KnightGenerator();

    public Knight(Color color) {
        super("Knight", color, GENERATOR);
    }

    public Knight(Color color, BoardSquare position) {
        super("Knight", color, GENERATOR, position);
    }

    public Knight(String name, Color color, BoardSquare position) {
        super(name, color, GENERATOR, position);
    }
}
