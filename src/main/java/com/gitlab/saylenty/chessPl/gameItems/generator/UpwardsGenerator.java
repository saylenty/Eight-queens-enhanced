package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UpwardsGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public UpwardsGenerator() {
    }

    @SuppressWarnings("WeakerAccess")
    public UpwardsGenerator(long limit) {
        super(limit);
    }

    /**
     * Generates the piece captureZone with positions that are above
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        return IntStream.iterate(y - 1, y1 -> y1 - 1)
                .limit(y + 1)
                .mapToObj(y1 -> new Space(x, y1));
    }
}
