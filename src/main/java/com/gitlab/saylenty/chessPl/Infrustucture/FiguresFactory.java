package com.gitlab.saylenty.chessPl.Infrustucture;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.*;

import java.awt.*;

public class FiguresFactory {

    public Bishop bishop(Color color, ChessBoard board){
        Bishop bishop = new Bishop(color);
        bishop.setBoard(board);
        return bishop;
    }

    public King king(Color color, ChessBoard board){
        King king = new King(color);
        king.setBoard(board);
        return king;
    }

    public Queen queen(Color color, ChessBoard board){
        Queen queen = new Queen(color);
        queen.setBoard(board);
        return queen;
    }

    public Rock rock(Color color, ChessBoard board){
        Rock rock = new Rock(color);
        rock.setBoard(board);
        return rock;
    }

    public Knight knight(Color color, ChessBoard board){
        Knight knight = new Knight(color);
        knight.setBoard(board);
        return knight;
    }
}
