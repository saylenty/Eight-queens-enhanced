/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems;

import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class GamePiecesFactory {

    private static final Logger log = LogManager.getLogger(GamePiecesFactory.class);

    public <T extends Piece> T createPiece(Class<T> clazz, Piece.Color color, ChessBoard board) {
        try {
            var constructor = clazz.getConstructor(Piece.Color.class);
            var t = constructor.newInstance(color);
            t.setChessBoard(board);
            return t;
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            log.error(e);
            throw new RuntimeException(
                    String.format("Could not instantiate desired figure of %s class", clazz.getName()));
        }
    }
}
