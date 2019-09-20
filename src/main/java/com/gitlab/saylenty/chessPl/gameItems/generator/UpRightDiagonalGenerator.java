package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public class UpRightDiagonalGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public UpRightDiagonalGenerator() {
    }

    @SuppressWarnings("WeakerAccess")
    public UpRightDiagonalGenerator(long limit) {
        super(limit);
    }

    /**
     * Generates the piece captureZone with positions that are on the up right diagonal
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        Stream.Builder<Space> stringBuilder = Stream.builder();
        while (++x < boardWidth && --y >= 0) {
            Space p = new Space(x, y);
            stringBuilder.add(p);
        }
        return stringBuilder.build();
    }
}
