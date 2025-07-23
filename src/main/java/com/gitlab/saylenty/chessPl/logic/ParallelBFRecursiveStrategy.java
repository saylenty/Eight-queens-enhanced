package com.gitlab.saylenty.chessPl.logic;

import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.GamePiecesFactory;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Parallel recursive placement strategy using ForkJoinPool.
 */
public class ParallelBFRecursiveStrategy implements PlacementStrategy {

    private final ChessBoard board;
    private final List<Piece> pieces;
    private final GamePiecesFactory factory = new GamePiecesFactory();

    public ParallelBFRecursiveStrategy(ChessBoard board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    @Override
    public int play() {
        // initialize board squares once to avoid concurrent initialization
        board.getBoardSquares();
        pieces.forEach(p -> p.setChessBoard(board));
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new PlacementTask(0, Collections.emptySet(), Collections.emptySet()));
    }

    private Piece createPiece(Piece prototype) {
        // create new piece of the same type and color
        return factory.createPiece((Class<? extends Piece>) prototype.getClass(), prototype.getColor(), board);
    }

    private class PlacementTask extends RecursiveTask<Integer> {
        private final int index;
        private final Set<BoardSquare> occupied;
        private final Set<BoardSquare> danger;

        PlacementTask(int index, Set<BoardSquare> occupied, Set<BoardSquare> danger) {
            this.index = index;
            this.occupied = occupied;
            this.danger = danger;
        }

        @Override
        protected Integer compute() {
            if (index == pieces.size()) {
                return 1;
            }
            Piece prototype = pieces.get(index);
            List<PlacementTask> tasks = new ArrayList<>();
            for (BoardSquare square : board.getBoardSquares()) {
                if (occupied.contains(square) || danger.contains(square)) {
                    continue;
                }
                Piece piece = createPiece(prototype);
                piece.setPosition(square);
                Set<BoardSquare> newOccupied = new HashSet<>(occupied);
                newOccupied.add(square);
                Set<BoardSquare> newDanger = new HashSet<>(danger);
                newDanger.addAll(piece.getCaptureZone());
                tasks.add(new PlacementTask(index + 1, newOccupied, newDanger));
            }
            if (tasks.isEmpty()) {
                return 0;
            }
            invokeAll(tasks);
            int result = 0;
            for (PlacementTask t : tasks) {
                result += t.join();
            }
            return result;
        }
    }
}
