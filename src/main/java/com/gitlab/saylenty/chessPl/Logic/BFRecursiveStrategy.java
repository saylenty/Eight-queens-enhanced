/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Logic;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.GameItems.Figures.Figure;
import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
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
    private int solutionsNumber = 0;

    public BFRecursiveStrategy(ChessBoard board, List<Figure> figures) {
        this.board = board;
        this.figures = figures;
    }

    @Override
    public int play() {
        // init resulting HashMap
        this.result = new LinkedHashMap<>();
        // run solver thread in a separate thread in order to save save some time
        Thread solverThread = new Thread(() -> recursiveStrategy(0));
        solverThread.start();
        // we need calculate the number of figures that are of the same class (e.g. King, Queen, etc.) and color
        // if these "same" figures is swapped -> the number of solutions shouldn't be changed
        // NOTE: this should be rewritten in a way with an additional figure class specific point pool to increase performance
        // use memory efficient variant here because the solution calc time usually takes some time and memory
        int c = 1;
        for (int i = 0; i < figures.size() - 1; i++) {
            Figure figure1 = figures.get(i);
            Class<? extends Figure> f1Class = figure1.getClass();
            Color f1Color = figure1.getColor();
            for (int j = i + 1; j < figures.size(); j++) {
                Figure figure2 = figures.get(j);
                Class<? extends Figure> figure2Class = figure2.getClass();
                Color figure2Color = figure2.getColor();
                if (f1Class.equals(figure2Class) && f1Color.equals(figure2Color)) {
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
        if (start >= figures.size()) {
            /*System.out.println("-------------------------------->");
            System.out.println("i = " + ++i);
            for (Map.Entry<Figure, Point> entry : result.entrySet()) {
                System.out.println(entry.getKey());
            }
            System.out.println("<---------------------------------");*/
            ++solutionsNumber;
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
                    // if another figure can't find its place on a board -> step back and move previous figure
                    board.removeFigure(current);
                    result.remove(current);
                }
            }
        }
        return false;
    }
}
