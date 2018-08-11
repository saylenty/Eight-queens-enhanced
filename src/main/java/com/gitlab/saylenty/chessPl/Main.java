/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.*;
import com.gitlab.saylenty.chessPl.Infrustucture.FiguresFactory;
import com.gitlab.saylenty.chessPl.Logic.BFRecursiveStrategy;
import com.gitlab.saylenty.chessPl.Logic.ChessGame;
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

        // create the game board
        ChessBoard chessBoard = new ChessBoard(9, 6);

        // create pieces and add them to the game board
        ArrayList<Piece> pieces = new ArrayList<>(6);

        FiguresFactory figuresFactory = new FiguresFactory();
        pieces.add(figuresFactory.createFigure(Queen.class, Color.black, chessBoard)); // one Queen
        pieces.add(figuresFactory.createFigure(Rock.class, Color.black, chessBoard)); // one Rock
        pieces.add(figuresFactory.createFigure(Knight.class, Color.black, chessBoard)); // one Knight
        pieces.add(figuresFactory.createFigure(Bishop.class, Color.black, chessBoard)); // one Bishop
        pieces.add(figuresFactory.createFigure(King.class, Color.black, chessBoard)); // first King
        pieces.add(figuresFactory.createFigure(King.class, Color.white, chessBoard)); // second King

        // print the message about game start
        System.out.println(String.format("Game has been started at %s",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))));

        // create timer
        Stopwatch timer = Stopwatch.createStarted();

        int result = game.start(new BFRecursiveStrategy(chessBoard, pieces));

        // stop the timer
        timer.stop();

        // print the result of the game
        System.out.println(String.format("The result is %d", result));
        System.out.println(String.format("Elapsed time (ms) %d", timer.elapsed(TimeUnit.MILLISECONDS)));

        // print the message about game end
        System.out.println(String.format("Game has been ended at %s",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))));
    }
}
