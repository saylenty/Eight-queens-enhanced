package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public class UpwardsGenerator implements RangeGenerationStrategy {

    /**
     * Updates the piece captureZone with positions that are above or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #generate(Space, int, int)} ()}.
     */
    @Override
    public Stream<Space> generate(Space location, int boardHeight, int boardWidth, int limit) {
        int x = location.getX();
        int y = location.getY();
        Stream.Builder<Space> streamBuilder = Stream.builder();
        while (--y >= 0 && limit-- != 0) {
            Space p = new Space(x, y);
            streamBuilder.add(p);
        }
        return streamBuilder.build();
    }
}
