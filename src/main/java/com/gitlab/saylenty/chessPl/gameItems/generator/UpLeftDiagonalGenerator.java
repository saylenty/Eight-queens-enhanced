package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public class UpLeftDiagonalGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public UpLeftDiagonalGenerator() {
    }

    @SuppressWarnings("WeakerAccess")
    public UpLeftDiagonalGenerator(long limit) {
        super(limit);
    }

    /**
     * Generates the piece captureZone with positions that are on the up left diagonal
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        Stream.Builder<Space> streamBuilder = Stream.builder();
        while (--x >= 0 && --y >= 0) {
            Space p = new Space(x, y);
            streamBuilder.add(p);
        }
        return streamBuilder.build();
    }
}
