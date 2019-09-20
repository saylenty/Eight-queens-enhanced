package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public class BottomLeftDiagonalGenerator extends AbstractRangeGenerationStrategy {

    @SuppressWarnings("WeakerAccess")
    public BottomLeftDiagonalGenerator() {
    }

    @SuppressWarnings("WeakerAccess")
    public BottomLeftDiagonalGenerator(long limit) {
        super(limit);
    }

    /**
     * Generates the piece captureZone with positions that are on the down left diagonal
     */
    @Override
    public Stream<Space> generateUnlimited(Space location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        Stream.Builder<Space> streamBuilder = Stream.builder();
        while (--x >= 0 && ++y < boardHeight) {
            Space p = new Space(x, y);
            streamBuilder.add(p);
        }
        return streamBuilder.build();
    }
}
