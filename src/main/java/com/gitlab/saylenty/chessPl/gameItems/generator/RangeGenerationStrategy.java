package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.stream.Stream;

public interface RangeGenerationStrategy {
    Stream<BoardSquare> generate(BoardSquare location, int boardHeight, int boardWidth);
}
