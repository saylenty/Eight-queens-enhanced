/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.logic;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Recursive chess pieces placement strategy for the chess game
 * Counts the number of available pieces permutations when they can't capture each other
 */
public class BFRecursiveStrategy implements PlacementStrategy {

    private final ChessBoard board;
    private final List<Piece> pieces;

    public BFRecursiveStrategy(ChessBoard board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    @Override
    public int play() {
        // calculate the number of figures possible combinations
        return recursiveStrategy(0, 0, set -> {
            // print result
            set.forEach(System.out::println);
            System.out.println();
        });
    }

    private int recursiveStrategy(int startIndex, int seed, Consumer<Stream<? extends Piece>> solutionConsumer) {
        if (startIndex == pieces.size()) {
            // the last piece on it's place, yet another solution is found
            solutionConsumer.accept(board.getBoardPieces());
            return seed + 1;
        }
        Piece current = pieces.get(startIndex);

        while (current.move()) {
            Set<Space> currentCaptureZone = current.getCaptureZone();
            if (board.getBoardPieces().map(Piece::getPosition)
                    .anyMatch(currentCaptureZone::contains)) {
                // current takes the piece
                continue;
            }
            // current piece doesn't take any other piece
            board.add(current);

            // continue with another piece
            seed = recursiveStrategy(startIndex + 1, seed, solutionConsumer);

            // if another piece can't find its place on a board -> step back and move previous piece
            board.remove(current);
        }
        return seed;
    }
}
