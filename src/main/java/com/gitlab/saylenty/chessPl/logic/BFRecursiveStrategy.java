/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.logic;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class BFRecursiveStrategy implements PlacementStrategy {

    private final ChessBoard board;
    private final List<Piece> pieces;
    private final Consumer<Stream<? extends Piece>> solutionConsumer;

    public BFRecursiveStrategy(ChessBoard board, List<Piece> pieces,
                                Consumer<Stream<? extends Piece>> solutionConsumer) {
        this.board = board;
        this.pieces = pieces;
        this.solutionConsumer = solutionConsumer;
    }

    public BFRecursiveStrategy(ChessBoard board, List<Piece> pieces) {
        this(board, pieces, set -> {
            set.forEach(System.out::println);
            System.out.println();
        });
    }

    @Override
    public int play() {
        return recursiveStrategy(0, 0, solutionConsumer);
    }

    private int recursiveStrategy(int startIndex, int seed, Consumer<Stream<? extends Piece>> consumer) {
        if (startIndex == pieces.size()) {
            consumer.accept(board.getBoardPieces());
            return seed + 1;
        }
        var current = pieces.get(startIndex);

        while (current.move()) {
            var currentCaptureZone = current.getCaptureZone();
            if (board.getBoardPieces().map(Piece::getPosition)
                    .anyMatch(currentCaptureZone::contains)) {
                continue;
            }
            board.add(current);
            seed = recursiveStrategy(startIndex + 1, seed, consumer);
            board.remove(current);
        }
        return seed;
    }
}
