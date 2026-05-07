package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public class RightwardsGenerator extends AbstractRangeGenerationStrategy {

    public RightwardsGenerator() {}

    public RightwardsGenerator(long limit) {
        super(limit);
    }

    @Override
    protected Stream<BoardSquare> generateUnlimited(BoardSquare location, int boardHeight, int boardWidth) {
        int y = location.getY();
        return Stream.iterate(new BoardSquare(location.getX() + 1, y),
                s -> s.getX() < boardWidth,
                s -> new BoardSquare(s.getX() + 1, y));
    }
}
