package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public abstract class AbstractRangeGenerationStrategy implements RangeGenerationStrategy {

    private final long limit;

    protected AbstractRangeGenerationStrategy() {
        this.limit = -1;
    }

    protected AbstractRangeGenerationStrategy(long limit) {
        this.limit = limit;
    }

    @Override
    public Stream<BoardSquare> generate(BoardSquare location, int boardHeight, int boardWidth) {
        Stream<BoardSquare> stream = generateUnlimited(location, boardHeight, boardWidth);
        return limit > 0 ? stream.limit(limit) : stream;
    }

    protected abstract Stream<BoardSquare> generateUnlimited(BoardSquare location, int boardHeight, int boardWidth);
}
