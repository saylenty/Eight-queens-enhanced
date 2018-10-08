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
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Main {

    public static void main(String[] args) {
        // print the message to indicate that the game has begun
        log.info("Game has been started at: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        // create timer
        Stopwatch timer = Stopwatch.createStarted();

        int result = complex();

        // stop the timer
        timer.stop();

        // print the message about game end
        log.info("Game has been ended at: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        log.info("Elapsed time (ms): {}", timer.elapsed(TimeUnit.MILLISECONDS));

        log.info("The result is: {}", result);
    }

    /**
     * This is complex variant, using:
     * Queens - 1
     * Rock - 1
     * Knight - 1
     * Bishop - 1
     * King - 2
     * @return number of possible solutions, which is: 20_136_752
     */
    private static int complex(){
        // create the ChessGame
        ChessGame game = new ChessGame();

        // create the game board
        ChessBoard chessBoard = new ChessBoard(9, 6);
        GamePiecesFactory gamePiecesFactory = new GamePiecesFactory();
        // create pieces and add them to the game board
        List<Piece> pieces = new LinkedList<>();
        pieces.add(gamePiecesFactory.createPiece(Queen.class, Piece.Color.BLACK, chessBoard)); // one Queen
        pieces.add(gamePiecesFactory.createPiece(Rock.class, Piece.Color.BLACK, chessBoard)); // one Rock
        pieces.add(gamePiecesFactory.createPiece(Knight.class, Piece.Color.BLACK, chessBoard)); // one Knight
        pieces.add(gamePiecesFactory.createPiece(Bishop.class, Piece.Color.BLACK, chessBoard)); // one Bishop
        pieces.add(gamePiecesFactory.createPiece(King.class, Piece.Color.BLACK, chessBoard)); // first King
        pieces.add(gamePiecesFactory.createPiece(King.class, Piece.Color.WHITE, chessBoard)); // second King

        return game.start(new BFRecursiveStrategy(chessBoard, pieces));
    }

    /**
     * This is a variant, using queens only:
     * Queens - {@code queensNumber}
     * @return number of possible solutions, on a board of [{@code boardHeight}, {@code boardWidth}]
     */
    private static int queens(int queensNumber, int boardHeight, int boardWidth){
        // create the ChessGame
        ChessGame game = new ChessGame();

        // create the game board
        ChessBoard chessBoard = new ChessBoard(boardHeight, boardWidth);
        GamePiecesFactory gamePiecesFactory = new GamePiecesFactory();
        // create pieces and add them to the game board
        List<Piece> pieces = new LinkedList<>();
        for (int i = 0; i < queensNumber; i++) {
            pieces.add(gamePiecesFactory.createPiece(Queen.class, Piece.Color.BLACK, chessBoard));
        }
        return game.start(new BFRecursiveStrategy(chessBoard, pieces));
    }
}
