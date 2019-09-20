package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public abstract class AbstractRangeGenerationStrategy implements RangeGenerationStrategy {

    private final long limit;

    @SuppressWarnings("WeakerAccess")
    public AbstractRangeGenerationStrategy() {
        limit = -1;
    }

    @SuppressWarnings("WeakerAccess")
    public AbstractRangeGenerationStrategy(long limit) {
        this.limit = limit;
    }

    @Override
    public Stream<Space> generate(Space location, int boardHeight, int boardWidth) {
        Stream<Space> spaceStream = generateUnlimited(location, boardHeight, boardWidth);
        if (limit > 0) {
            spaceStream = spaceStream.limit(limit);
        }
        return spaceStream;
    }

    public abstract Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth);
}
