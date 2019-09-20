package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LeftwardsGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public LeftwardsGenerator(long limit) {
        super(limit);
    }

    @SuppressWarnings("WeakerAccess")
    public LeftwardsGenerator() {
    }

    /**
     * Generates the piece captureZone with positions that are on the left
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();

        return IntStream.iterate(x -1, x1 -> x1 - 1)
                .limit(x + 1)
                .mapToObj(x1 -> new Space(x1, y));
    }
}
