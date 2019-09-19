package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public interface RangeGenerationStrategy {

    default Stream<Space> generate(Space location, int boardHeight, int boardWidth) {
        return generate(location, boardHeight, boardWidth, -1);
    }

    Stream<Space> generate(Space location, int boardHeight, int boardWidth, int limit);
}
