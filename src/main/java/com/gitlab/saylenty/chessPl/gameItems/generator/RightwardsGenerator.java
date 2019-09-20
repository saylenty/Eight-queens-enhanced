package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RightwardsGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public RightwardsGenerator() {
    }

    @SuppressWarnings("WeakerAccess")
    public RightwardsGenerator(long limit) {
        super(limit);
    }

    /**
     * Generates the piece captureZone with positions that are on the right
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        return IntStream.range(x + 1, boardWidth).mapToObj(x1 -> new Space(x1, y));
    }
}
