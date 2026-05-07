/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiStraightLineGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;

public final class Rook extends Piece {

    private static final RangeGenerationStrategy GENERATOR = new BiStraightLineGenerator();

    public Rook(Color color) {
        super("Rook", color, GENERATOR);
    }

    public Rook(Color color, BoardSquare position) {
        super("Rook", color, GENERATOR, position);
    }

    public Rook(String name, Color color, BoardSquare position) {
        super(name, color, GENERATOR, position);
    }
}
