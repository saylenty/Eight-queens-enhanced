package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    void captureZoneContainsOwnPosition() {
        var board = new ChessBoard(5, 5);
        var position = new BoardSquare(2, 2);
        var queen = new Queen(Piece.Color.BLACK, position);
        queen.setChessBoard(board);
        assertThat(queen.getCaptureZone()).contains(position);
    }

    @Test
    void captureZoneCoversSameRowColumnAndAllDiagonals() {
        var board = new ChessBoard(5, 5);
        var queen = new Queen(Piece.Color.BLACK, new BoardSquare(2, 2));
        queen.setChessBoard(board);
        assertThat(queen.getCaptureZone()).containsExactlyInAnyOrder(
            new BoardSquare(2, 2),
            new BoardSquare(2, 1), new BoardSquare(2, 0),
            new BoardSquare(2, 3), new BoardSquare(2, 4),
            new BoardSquare(1, 2), new BoardSquare(0, 2),
            new BoardSquare(3, 2), new BoardSquare(4, 2),
            new BoardSquare(1, 1), new BoardSquare(0, 0),
            new BoardSquare(3, 3), new BoardSquare(4, 4),
            new BoardSquare(3, 1), new BoardSquare(4, 0),
            new BoardSquare(1, 3), new BoardSquare(0, 4)
        );
    }
}
