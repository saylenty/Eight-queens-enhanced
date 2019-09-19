/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.generator.BiDiagonalGenerator;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(new BiDiagonalGenerator(), "Bishop", color);
    }

    public Bishop(Color color, Space position) {
        super("Bishop", color, new BiDiagonalGenerator(), position);
    }

    public Bishop(String name, Color color, Space position) {
        super(name, color, new BiDiagonalGenerator(), position);
    }
}
