package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    void captureZoneContainsOwnPosition() {
        var board = new ChessBoard(5, 5);
        var position = new BoardSquare(2, 2);
        var knight = new Knight(Piece.Color.BLACK, position);
        knight.setChessBoard(board);
        assertThat(knight.getCaptureZone()).contains(position);
    }

    @Test
    void captureZoneCoversLShapeSquares() {
        var board = new ChessBoard(5, 5);
        var knight = new Knight(Piece.Color.BLACK, new BoardSquare(2, 2));
        knight.setChessBoard(board);
        assertThat(knight.getCaptureZone()).containsExactlyInAnyOrder(
            new BoardSquare(2, 2),
            new BoardSquare(1, 0), new BoardSquare(3, 0),
            new BoardSquare(0, 1), new BoardSquare(0, 3),
            new BoardSquare(1, 4), new BoardSquare(3, 4),
            new BoardSquare(4, 1), new BoardSquare(4, 3)
        );
    }
}
