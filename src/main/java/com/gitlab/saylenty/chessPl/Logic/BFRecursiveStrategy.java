/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Logic;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.Piece;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

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
        // run solver thread in a separate thread in order to save save some time
        FutureTask<Integer> task = new FutureTask<>(() -> recursiveStrategy(0, 0, set -> {
            // print result
            /*for (Piece piece : set) {
                System.out.println(piece + " ");
            }
            System.out.println();*/
        }));
        Thread solverThread = new Thread(task);
        solverThread.start();
        // we need calculate the number of pieces that are of the same class (e.g. King, Queen, etc.) and color
        // if these "same" pieces is swapped -> the number of solutions shouldn't be changed
        // TODO think how to optimize the algorithm so that the code below become unnecessary
        int c = 1;
        for (int i = 0; i < pieces.size() - 1; i++) {
            Piece piece1 = pieces.get(i);
            Class<? extends Piece> f1Class = piece1.getClass();
            Color f1Color = piece1.getColor();
            for (int j = i + 1; j < pieces.size(); j++) {
                Piece piece2 = pieces.get(j);
                Class<? extends Piece> piece2Class = piece2.getClass();
                Color piece2Color = piece2.getColor();
                if (f1Class.equals(piece2Class) && f1Color.equals(piece2Color)) {
                    ++c;
                }
            }
        }
        try {
            return task.get() / c;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int recursiveStrategy(int startIndex, int seed, Consumer<Set<Piece>> solutionConsumer) {
        if (startIndex == pieces.size()) {
            solutionConsumer.accept(board.getBoardPieces());
            return seed + 1;
        }
        Piece current = pieces.get(startIndex);

        OUTER:
        while (current.move()) {
            for (Piece piece : board.getBoardPieces()) {
                if (piece.getCaptureZone().contains(current.getPosition()) ||
                        current.getCaptureZone().contains(piece.getPosition())) {
                    continue OUTER;
                }
            }
            // current piece take zone doesn't intersect with other's
            board.addPiece(current);

            // continue with another piece
            seed = recursiveStrategy(startIndex + 1, seed, solutionConsumer);

            // if another piece can't find its place on a board -> step back and move previous piece
            board.removePiece(current);
        }
        return seed;
    }
}
