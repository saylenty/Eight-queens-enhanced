package com.gitlab.saylenty.chessPl.gameItems;

import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    @Test
    void getBoardSquaresReturnsWidthTimesHeightSquares() {
        var board = new ChessBoard(4, 5);
        assertThat(board.getBoardSquares()).hasSize(20);
    }

    @Test
    void getBoardSquaresIsIdempotent() {
        var board = new ChessBoard(3, 3);
        var first = board.getBoardSquares();
        var second = board.getBoardSquares();
        assertThat(first).isEqualTo(second);
    }

    @Test
    void isEmptyOnFreshBoard() {
        var board = new ChessBoard(4, 4);
        assertThat(board.isEmpty()).isTrue();
    }

    @Test
    void addPiecePlacesItOnBoard() {
        var board = new ChessBoard(5, 5);
        board.getBoardSquares();
        var queen = new Queen(Piece.Color.BLACK, new BoardSquare(2, 2));
        queen.setChessBoard(board);
        board.add(queen);
        assertThat(board.getBoardPieces()).anyMatch(queen::equals);
        assertThat(board.isEmpty()).isFalse();
    }

    @Test
    void addPieceLocksItsCaptureZoneSquares() {
        var board = new ChessBoard(5, 5);
        board.getBoardSquares();
        var queen = new Queen(Piece.Color.BLACK, new BoardSquare(2, 2));
        queen.setChessBoard(board);
        board.add(queen);
        assertThat(board.getCaptureZone().count()).isEqualTo(17);
        assertThat(board.getFreeZone().count()).isEqualTo(8);
    }

    @Test
    void removePieceRestoresBoardToEmptyState() {
        var board = new ChessBoard(5, 5);
        board.getBoardSquares();
        var queen = new Queen(Piece.Color.BLACK, new BoardSquare(2, 2));
        queen.setChessBoard(board);
        board.add(queen);
        board.remove(queen);
        assertThat(board.isEmpty()).isTrue();
        assertThat(board.getFreeZone().count()).isEqualTo(25);
    }
}
