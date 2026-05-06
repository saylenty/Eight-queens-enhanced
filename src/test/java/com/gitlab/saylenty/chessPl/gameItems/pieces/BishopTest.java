package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    void captureZoneContainsOwnPosition() {
        var board = new ChessBoard(5, 5);
        var position = new BoardSquare(2, 2);
        var bishop = new Bishop(Piece.Color.BLACK, position);
        bishop.setChessBoard(board);
        assertThat(bishop.getCaptureZone()).contains(position);
    }

    @Test
    void captureZoneCoversDiagonalsOnly() {
        var board = new ChessBoard(5, 5);
        var bishop = new Bishop(Piece.Color.BLACK, new BoardSquare(2, 2));
        bishop.setChessBoard(board);
        assertThat(bishop.getCaptureZone()).containsExactlyInAnyOrder(
            new BoardSquare(2, 2),
            new BoardSquare(1, 1), new BoardSquare(0, 0),
            new BoardSquare(3, 3), new BoardSquare(4, 4),
            new BoardSquare(3, 1), new BoardSquare(4, 0),
            new BoardSquare(1, 3), new BoardSquare(0, 4)
        );
    }
}
