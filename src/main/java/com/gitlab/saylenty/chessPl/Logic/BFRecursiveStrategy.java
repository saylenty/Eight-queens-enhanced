/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Logic;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.Piece;

import java.awt.*;
import java.util.List;

/**
 * Recursive chess pieces placement strategy for the chess game
 * Counts the number of available pieces permutations when they can't capture each other
 */
public class BFRecursiveStrategy implements PlacementStrategy {

    private final ChessBoard board;
    private final List<Piece> pieces;
    private int solutionsNumber = 0;

    public BFRecursiveStrategy(ChessBoard board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    @Override
    public int play() {
        // run solver thread in a separate thread in order to save save some time
        Thread solverThread = new Thread(() -> recursiveStrategy(0));
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
                    c++;
                }
            }
        }
        try {
            solverThread.join();
        } catch (InterruptedException e) {
            // error occurred
            return -1;
        }
        return solutionsNumber / c;
    }

    private boolean recursiveStrategy(int start) {
        if (start >= pieces.size()) {
            /*System.out.println("-------------------------------->");
            System.out.println("i = " + ++i);
            for (Map.Entry<Piece, Space> entry : result.entrySet()) {
                System.out.println(entry.getKey());
            }
            System.out.println("<---------------------------------");*/
            ++solutionsNumber;
            // continue to search for another possible solutions
            return false;
        }

        Piece current = pieces.get(start);
        while (current.move()) {
            boolean intersects = board.getBoardPieces().stream()
                    .anyMatch(piece -> piece.getCaptureZone().contains(current.getPosition()) ||
                            current.getCaptureZone().contains(piece.getPosition()));
            if (!intersects) {
                // current piece range doesn't intersects with other piece range
                board.addPiece(current);

                // continue with another piece
                if (recursiveStrategy(start + 1)) {
                    return true;
                }

                // if another piece can't find its place on a board -> step back and move previous piece
                board.removePiece(current);
            }
        }
        return false;
    }
}
