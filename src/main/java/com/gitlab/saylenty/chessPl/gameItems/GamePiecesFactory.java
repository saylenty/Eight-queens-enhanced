/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems;

import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Factory for pieces creation
 */
public class GamePiecesFactory {

    /**
     * Creates a game piece and sets it on the board
     *
     * @param clazz a piece class
     * @param color a piece color
     * @param board a board to associate the piece with
     * @return created piece
     */
    public <T extends Piece> T createPiece(Class<T> clazz, Piece.Color color, ChessBoard board) {
        T t;
        try {
            Constructor<T> constructor = clazz.getConstructor(Piece.Color.class);
            t = constructor.newInstance(color);
            t.setChessBoard(board);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    String.format("Could not instantiate desired figure of %s class", clazz.getName()));
        }
        return t;
    }
}
