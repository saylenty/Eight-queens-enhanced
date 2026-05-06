package com.gitlab.saylenty.chessPl.gameItems;

import com.gitlab.saylenty.chessPl.gameItems.pieces.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GamePiecesFactoryTest {

    private final GamePiecesFactory factory = new GamePiecesFactory();
    private final ChessBoard board = new ChessBoard(5, 5);

    static Stream<Class<? extends Piece>> pieceTypes() {
        return Stream.of(Queen.class, Rock.class, Bishop.class, King.class, Knight.class);
    }

    @ParameterizedTest
    @MethodSource("pieceTypes")
    void createPieceReturnsCorrectSubtypeWithBoardSet(Class<? extends Piece> clazz) {
        var piece = factory.createPiece(clazz, Piece.Color.BLACK, board);
        assertThat(piece).isInstanceOf(clazz);
        assertThat(piece.getChessBoard()).isSameAs(board);
    }

    @SuppressWarnings("unchecked")
    @Test
    void createPieceWithIncompatibleClassThrowsRuntimeException() {
        assertThatThrownBy(() -> factory.createPiece((Class) Piece.class, Piece.Color.BLACK, board))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Could not instantiate");
    }
}
