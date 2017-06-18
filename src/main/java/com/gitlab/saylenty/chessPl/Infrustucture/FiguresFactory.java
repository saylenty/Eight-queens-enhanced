/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Infrustucture;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.*;

import java.awt.*;

/**
 * Factory for figures creation
 */
public class FiguresFactory {

    /**
     * Creates a Bishop figure and sets is to a board
     * @param color a figure color
     * @param board a board to place the figure at
     * @return created figure
     */
    public Bishop bishop(Color color, ChessBoard board){
        Bishop bishop = new Bishop(color);
        bishop.setBoard(board);
        return bishop;
    }

    /**
     * Creates a King figure and sets is to a board
     * @param color a figure color
     * @param board a board to place the figure at
     * @return created figure
     */
    public King king(Color color, ChessBoard board){
        King king = new King(color);
        king.setBoard(board);
        return king;
    }

    /**
     * Creates a Queen figure and sets is to a board
     * @param color a figure color
     * @param board a board to place the figure at
     * @return created figure
     */
    public Queen queen(Color color, ChessBoard board){
        Queen queen = new Queen(color);
        queen.setBoard(board);
        return queen;
    }

    /**
     * Creates a Rock figure and sets is to a board
     * @param color a figure color
     * @param board a board to place the figure at
     * @return created figure
     */
    public Rock rock(Color color, ChessBoard board){
        Rock rock = new Rock(color);
        rock.setBoard(board);
        return rock;
    }

    /**
     * Creates a Knight figure and sets is to a board
     * @param color a figure color
     * @param board a board to place the figure at
     * @return created figure
     */
    public Knight knight(Color color, ChessBoard board){
        Knight knight = new Knight(color);
        knight.setBoard(board);
        return knight;
    }
}
