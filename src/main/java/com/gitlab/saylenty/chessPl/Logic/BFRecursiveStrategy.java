/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Logic;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.Figure;
import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Recursive chess figures placement strategy for the chess game
 * Counts the number of available figures permutations when they can't capture each other
 */
public class BFRecursiveStrategy implements PlacementStrategy {

    private final ChessBoard board;
    private final List<Figure> figures;
    private HashMap<Figure, Point> result;
    private int i = 0;

    public BFRecursiveStrategy(ChessBoard board, List<Figure> figures) {
        this.board = board;
        this.figures = figures;
        this.result = new LinkedHashMap<>();
    }

    @Override
    public int play() {
        recursiveStrategy(0);
        return i;
    }

    private boolean recursiveStrategy(int start) {
        if (start >= figures.size()) {
            /*System.out.println("-------------------------------->");
            System.out.println("i = " + ++i);
            for (Map.Entry<Figure, Point> entry : result.entrySet()) {
                System.out.println(entry.getKey());
            }
            System.out.println("<---------------------------------");*/
            i++;
            return false;
        }

        Figure current = figures.get(start);
        while (current.move()) {
            boolean intersects = result.keySet().stream()
                    .anyMatch(figure -> figure.getRange().contains(current.getPosition()) ||
                            current.getRange().contains(figure.getPosition()));
            if (!intersects) { // current figure range doesn't intersects with other figures range
                board.addFigure(current);
                result.put(current, current.getPosition()); // add figure to result list
                if (recursiveStrategy(start + 1)) { // continue with another figure
                    return true;
                } else {
                    // if another figure can't find it place on a board -> step back and move previous figure
                    board.removeFigure(current);
                    result.remove(current);
                }
            }
        }
        return false;
    }
}
