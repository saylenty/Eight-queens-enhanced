package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public class DownwardsGenerator extends AbstractRangeGenerationStrategy {

    public DownwardsGenerator() {}

    public DownwardsGenerator(long limit) {
        super(limit);
    }

    @Override
    protected Stream<BoardSquare> generateUnlimited(BoardSquare location, int boardHeight, int boardWidth) {
        int x = location.getX();
        return Stream.iterate(new BoardSquare(x, location.getY() + 1),
                s -> s.getY() < boardHeight,
                s -> new BoardSquare(x, s.getY() + 1));
    }
}
