package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public class BottomRightDiagonalGenerator implements RangeGenerationStrategy {

    @Override
    public Stream<Space> generate(Space location, int boardHeight, int boardWidth, int limit) {
        int x = location.getX();
        int y = location.getY();
        Stream.Builder<Space> streamBuilder = Stream.builder();
        while (++x < boardWidth && ++y < boardHeight && limit-- != 0) {
            Space p = new Space(x, y);
            streamBuilder.add(p);
        }
        return streamBuilder.build();
    }
}
