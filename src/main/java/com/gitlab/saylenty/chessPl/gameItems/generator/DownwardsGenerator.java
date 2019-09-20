package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DownwardsGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public DownwardsGenerator() {
    }

    @SuppressWarnings("WeakerAccess")
    public DownwardsGenerator(long limit) {
        super(limit);
    }

    /**
     * Generates the piece captureZone with positions that are below
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        return IntStream.range(y + 1, boardHeight).mapToObj(y1 -> new Space(x, y1));
    }
}
