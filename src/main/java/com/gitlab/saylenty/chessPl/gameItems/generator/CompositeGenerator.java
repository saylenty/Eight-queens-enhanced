package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.List;
import java.util.stream.Stream;

public class CompositeGenerator implements RangeGenerationStrategy {

    private final List<RangeGenerationStrategy> strategies;

    public CompositeGenerator(RangeGenerationStrategy... strategies) {
        this.strategies = List.of(strategies);
    }

    @Override
    public Stream<BoardSquare> generate(BoardSquare location, int boardHeight, int boardWidth) {
        return strategies.stream()
                .flatMap(s -> s.generate(location, boardHeight, boardWidth));
    }
}
