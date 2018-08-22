/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.GamePiecesFactory;
import com.gitlab.saylenty.chessPl.gameItems.pieces.*;
import com.gitlab.saylenty.chessPl.logic.BFRecursiveStrategy;
import com.gitlab.saylenty.chessPl.logic.ChessGame;
import com.google.common.base.Stopwatch;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // create the ChessGame
        ChessGame game = new ChessGame();

        int queensNumber = 4;

        // create the game board
        ChessBoard chessBoard = new ChessBoard(queensNumber, queensNumber);

        // create pieces and add them to the game board
        ArrayList<Piece> pieces = new ArrayList<>(queensNumber);

        GamePiecesFactory gamePiecesFactory = new GamePiecesFactory();
        for (int i = 0; i < queensNumber; i++) {
            pieces.add(gamePiecesFactory.createPiece(Queen.class, Color.black, chessBoard));
        }

        // print the message about game start
        System.out.println(String.format("Game has been started at: %s",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))));

        // create timer
        Stopwatch timer = Stopwatch.createStarted();

        int result = game.start(new BFRecursiveStrategy(chessBoard, pieces));

        // stop the timer
        timer.stop();

        // print the message about game end
        System.out.println(String.format("Game has been ended at: %s",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))));

        System.out.println(String.format("Elapsed time (ms): %d", timer.elapsed(TimeUnit.MILLISECONDS)));

        System.out.println(String.format("The result is: %d", result));
    }
}
