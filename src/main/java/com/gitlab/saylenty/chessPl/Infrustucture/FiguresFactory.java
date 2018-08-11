/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Infrustucture;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.Piece;

import java.awt.*;

/**
 * Factory for figures creation
 */
public class FiguresFactory {

    /**
     * Creates a figure and sets it to a board
     * @param clazz a figure class
     * @param color a figure color
     * @param board a board to place the figure at
     * @return created figure
     */
    public <T extends Piece> T createFigure(Class<T> clazz, Color color, ChessBoard board) {
        T t;
        try {
            t = clazz.newInstance();
            t.setColor(color);
            t.setBoard(board);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
        return t;
    }
}
