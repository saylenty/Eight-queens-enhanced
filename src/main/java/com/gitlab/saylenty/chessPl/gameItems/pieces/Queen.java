/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiDiagonalGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiStraightLineGenerator;
import com.gitlab.saylenty.chessPl.gameItems.generator.CompositeGenerator;

public class Queen extends Piece {

    public Queen(Color color) {
        super(new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator()), "Queen", color);
    }

    public Queen(Color color, Space position) {
        super("Queen", color, new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator()), position);
    }

    public Queen(String name, Color color, Space position) {
        super(name, color, new CompositeGenerator(new BiDiagonalGenerator(), new BiStraightLineGenerator()), position);
    }
}
