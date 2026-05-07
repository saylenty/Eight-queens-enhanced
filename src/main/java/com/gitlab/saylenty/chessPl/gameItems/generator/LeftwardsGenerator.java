package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public class LeftwardsGenerator extends AbstractRangeGenerationStrategy {

    public LeftwardsGenerator() {}

    public LeftwardsGenerator(long limit) {
        super(limit);
    }

    @Override
    protected Stream<BoardSquare> generateUnlimited(BoardSquare location, int boardHeight, int boardWidth) {
        int y = location.getY();
        return Stream.iterate(new BoardSquare(location.getX() - 1, y),
                s -> s.getX() >= 0,
                s -> new BoardSquare(s.getX() - 1, y));
    }
}
