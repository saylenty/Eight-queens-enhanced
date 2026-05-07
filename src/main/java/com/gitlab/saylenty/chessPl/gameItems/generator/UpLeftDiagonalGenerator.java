package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public class UpLeftDiagonalGenerator extends AbstractRangeGenerationStrategy {

    public UpLeftDiagonalGenerator() {}

    public UpLeftDiagonalGenerator(long limit) {
        super(limit);
    }

    @Override
    protected Stream<BoardSquare> generateUnlimited(BoardSquare location, int boardHeight, int boardWidth) {
        return Stream.iterate(new BoardSquare(location.getX() - 1, location.getY() - 1),
                s -> s.getX() >= 0 && s.getY() >= 0,
                s -> new BoardSquare(s.getX() - 1, s.getY() - 1));
    }
}
