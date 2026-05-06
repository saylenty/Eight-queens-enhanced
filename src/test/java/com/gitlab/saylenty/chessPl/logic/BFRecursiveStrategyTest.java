package com.gitlab.saylenty.chessPl.logic;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.GamePiecesFactory;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Queen;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class BFRecursiveStrategyTest {

    private static final GamePiecesFactory factory = new GamePiecesFactory();

    @ParameterizedTest
    @CsvSource({
        "1, 1, 1",
        "4, 4, 2",
        "5, 5, 10",
        "6, 6, 4",
        "8, 8, 92"
    })
    void nQueensReturnsExpectedSolutionCount(int queens, int boardSize, int expected) {
        var board = new ChessBoard(boardSize, boardSize);
        var pieces = new ArrayList<Piece>();
        for (int i = 0; i < queens; i++) {
            pieces.add(factory.createPiece(Queen.class, Piece.Color.BLACK, board));
        }
        var strategy = new BFRecursiveStrategy(board, pieces, stream -> {});
        assertThat(strategy.play()).isEqualTo(expected);
    }
}
