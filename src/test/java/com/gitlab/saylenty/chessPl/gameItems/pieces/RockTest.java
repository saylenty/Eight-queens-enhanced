package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RockTest {

    @Test
    void captureZoneContainsOwnPosition() {
        var board = new ChessBoard(5, 5);
        var position = new BoardSquare(2, 2);
        var rock = new Rock(Piece.Color.BLACK, position);
        rock.setChessBoard(board);
        assertThat(rock.getCaptureZone()).contains(position);
    }

    @Test
    void captureZoneCoversSameRowAndColumnOnly() {
        var board = new ChessBoard(5, 5);
        var rock = new Rock(Piece.Color.BLACK, new BoardSquare(2, 2));
        rock.setChessBoard(board);
        assertThat(rock.getCaptureZone()).containsExactlyInAnyOrder(
            new BoardSquare(2, 2),
            new BoardSquare(2, 1), new BoardSquare(2, 0),
            new BoardSquare(2, 3), new BoardSquare(2, 4),
            new BoardSquare(1, 2), new BoardSquare(0, 2),
            new BoardSquare(3, 2), new BoardSquare(4, 2)
        );
    }
}
