package com.gitlab.saylenty.chessPl.gameItems.generator;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.Arrays;
import java.util.stream.Stream;

public class KnightGenerator implements RangeGenerationStrategy {

    private static final int[][] OFFSETS = {{-1, -2}, {1, -2}, {-2, -1}, {2, -1}, {-2, 1}, {2, 1}, {-1, 2}, {1, 2}};

    @Override
    public Stream<BoardSquare> generate(BoardSquare location, int boardHeight, int boardWidth) {
        int x = location.getX();
        int y = location.getY();
        return Arrays.stream(OFFSETS)
                .map(o -> new BoardSquare(x + o[0], y + o[1]))
                .filter(s -> s.getX() >= 0 && s.getX() < boardWidth
                        && s.getY() >= 0 && s.getY() < boardHeight);
    }
}
