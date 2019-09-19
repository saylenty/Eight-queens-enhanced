package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public class UpRightDiagonalGenerator implements RangeGenerationStrategy {

    /**
     * Updates the piece captureZone with positions that are on the up right diagonal or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #generate(Space, int, int)} ()}.
     */
    @Override
    public Stream<Space> generate(Space location, int boardHeight, int boardWidth, int limit) {
        int x = location.getX();
        int y = location.getY();
        Stream.Builder<Space> stringBuilder = Stream.builder();
        while (++x < boardWidth && --y >= 0 && limit-- != 0) {
            Space p = new Space(x, y);
            stringBuilder.add(p);
        }
        return stringBuilder.build();
    }
}
