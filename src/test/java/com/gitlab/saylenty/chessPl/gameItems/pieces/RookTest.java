package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    void captureZoneContainsOwnPosition() {
        var board = new ChessBoard(5, 5);
        var position = new BoardSquare(2, 2);
        var rook = new Rook(Piece.Color.BLACK, position);
        rook.setChessBoard(board);
        assertThat(rook.getCaptureZone()).contains(position);
    }

    @Test
    void captureZoneCoversSameRowAndColumnOnly() {
        var board = new ChessBoard(5, 5);
        var rook = new Rook(Piece.Color.BLACK, new BoardSquare(2, 2));
        rook.setChessBoard(board);
        assertThat(rook.getCaptureZone()).containsExactlyInAnyOrder(
            new BoardSquare(2, 2),
            new BoardSquare(2, 1), new BoardSquare(2, 0),
            new BoardSquare(2, 3), new BoardSquare(2, 4),
            new BoardSquare(1, 2), new BoardSquare(0, 2),
            new BoardSquare(3, 2), new BoardSquare(4, 2)
        );
    }
}
