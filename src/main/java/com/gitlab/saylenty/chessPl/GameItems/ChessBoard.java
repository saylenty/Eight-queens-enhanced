package com.gitlab.saylenty.chessPl.GameItems;

import com.gitlab.saylenty.chessPl.GameItems.Figures.Figure;
import com.gitlab.saylenty.chessPl.Infrustucture.Point;
import com.gitlab.saylenty.chessPl.Infrustucture.PointsPool;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * Created by Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 * </p>
 */
public class ChessBoard {
    private int height;
    private int width;
    private final PointsPool pointsPool;
    private final Set<Figure> boardFigures;
    private final Set<Point> allBoardPoints;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Set<Figure> getBoardFigures() {
        return boardFigures;
    }

    public ChessBoard(int height, int width) {
        this.width = width;
        this.height = height;
        boardFigures = new HashSet<>();
        allBoardPoints = new HashSet<>();
        pointsPool = PointsPool.getInstance();
    }

    public Set<Point> getAllBoardPoints() {
        if (!allBoardPoints.isEmpty()) {
            return allBoardPoints;
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                allBoardPoints.add(pointsPool.valueOf(x, y));
            }
        }
        return allBoardPoints;
    }

    public Set<Point> getFreePoints() {
        Set<Point> difference = new HashSet<>(this.getAllBoardPoints());
        difference.removeAll(boardFigures.stream().flatMap(x -> x.getRange().stream()).collect(Collectors.toSet()));
        return difference;
    }

    public boolean addFigure(Figure figure) {
        return boardFigures.add(figure);
    }

    public boolean removeFigure(Figure figure) {
        return boardFigures.remove(figure);
    }
}
