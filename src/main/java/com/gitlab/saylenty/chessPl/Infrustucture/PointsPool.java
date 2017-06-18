/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Infrustucture;

import java.util.HashMap;

/**
 * Special class that holds all points instances which were used during the game (for optimization)
 */
public class PointsPool {
    private static PointsPool ourInstance = new PointsPool();

    public static PointsPool getInstance() {
        return ourInstance;
    }

    private HashMap<Integer, Point> points;

    private PointsPool() {
        points = new HashMap<>();
    }

    /**
     * Get a point from the pool or create a new one
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return a requested point
     */
    public Point valueOf(int x, int y){
        int hash = 31 * x + y;
        if (points.containsKey(hash)) {
            return points.get(hash);
        }
        Point p = new Point(x, y);
        points.put(hash, p);
        return p;
    }
}
