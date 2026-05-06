/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.GamePiecesFactory;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Bishop;
import com.gitlab.saylenty.chessPl.gameItems.pieces.King;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Knight;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Queen;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Rock;
import com.gitlab.saylenty.chessPl.logic.BFRecursiveStrategy;
import com.gitlab.saylenty.chessPl.logic.ChessGame;
import com.google.common.base.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Game has been started at: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        var timer = Stopwatch.createStarted();

        int result = complex();

        timer.stop();

        log.info("Game has been ended at: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        log.info("Elapsed time (ms): {}", timer.elapsed(TimeUnit.MILLISECONDS));

        log.info("The result is: {}", result);
    }

    private static int complex() {
        var game = new ChessGame();
        var chessBoard = new ChessBoard(9, 6);
        var gamePiecesFactory = new GamePiecesFactory();
        var pieces = new ArrayList<Piece>();
        pieces.add(gamePiecesFactory.createPiece(Queen.class, Piece.Color.BLACK, chessBoard));
        pieces.add(gamePiecesFactory.createPiece(Rock.class, Piece.Color.BLACK, chessBoard));
        pieces.add(gamePiecesFactory.createPiece(Knight.class, Piece.Color.BLACK, chessBoard));
        pieces.add(gamePiecesFactory.createPiece(Bishop.class, Piece.Color.BLACK, chessBoard));
        pieces.add(gamePiecesFactory.createPiece(King.class, Piece.Color.BLACK, chessBoard));
        pieces.add(gamePiecesFactory.createPiece(King.class, Piece.Color.WHITE, chessBoard));
        return game.start(new BFRecursiveStrategy(chessBoard, pieces));
    }

    private static int queens(int queensNumber, int boardHeight, int boardWidth) {
        var game = new ChessGame();
        var chessBoard = new ChessBoard(boardHeight, boardWidth);
        var gamePiecesFactory = new GamePiecesFactory();
        var pieces = new ArrayList<Piece>();
        for (int i = 0; i < queensNumber; i++) {
            pieces.add(gamePiecesFactory.createPiece(Queen.class, Piece.Color.BLACK, chessBoard));
        }
        return game.start(new BFRecursiveStrategy(chessBoard, pieces));
    }
}
