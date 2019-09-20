package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.stream.Stream;

public interface RangeGenerationStrategy {

    Stream<Space> generate(Space location, int boardHeight, int boardWidth);
}
