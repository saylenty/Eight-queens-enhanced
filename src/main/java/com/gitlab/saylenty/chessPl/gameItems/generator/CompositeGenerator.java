package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.Space;

import java.util.Arrays;
import java.util.stream.Stream;

public class CompositeGenerator implements RangeGenerationStrategy {

    private RangeGenerationStrategy[] strategies;

    public CompositeGenerator(RangeGenerationStrategy... strategies) {
        this.strategies = strategies;
    }

    @Override
    public Stream<Space> generate(Space location, int boardHeight, int boardWidth, int limit) {
        return Arrays.stream(strategies)
                .flatMap(strategy -> strategy.generate(location, boardHeight, boardWidth, limit));
    }
}
