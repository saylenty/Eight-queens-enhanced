package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public class UpwardsGenerator extends AbstractRangeGenerationStrategy {

    public UpwardsGenerator() {}

    public UpwardsGenerator(long limit) {
        super(limit);
    }

    @Override
    protected Stream<BoardSquare> generateUnlimited(BoardSquare location, int boardHeight, int boardWidth) {
        int x = location.getX();
        return Stream.iterate(new BoardSquare(x, location.getY() - 1),
                s -> s.getY() >= 0,
                s -> new BoardSquare(x, s.getY() - 1));
    }
}
