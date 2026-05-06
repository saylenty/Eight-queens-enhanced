package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    void captureZoneContainsOwnPosition() {
        var board = new ChessBoard(5, 5);
        var position = new BoardSquare(2, 2);
        var king = new King(Piece.Color.BLACK, position);
        king.setChessBoard(board);
        assertThat(king.getCaptureZone()).contains(position);
    }

    @Test
    void captureZoneCoversAllAdjacentSquares() {
        var board = new ChessBoard(5, 5);
        var king = new King(Piece.Color.BLACK, new BoardSquare(2, 2));
        king.setChessBoard(board);
        assertThat(king.getCaptureZone()).containsExactlyInAnyOrder(
            new BoardSquare(2, 2),
            new BoardSquare(1, 2),
            new BoardSquare(3, 2),
            new BoardSquare(2, 1),
            new BoardSquare(2, 3),
            new BoardSquare(1, 1),
            new BoardSquare(3, 3),
            new BoardSquare(3, 1),
            new BoardSquare(1, 3)
        );
    }
}
