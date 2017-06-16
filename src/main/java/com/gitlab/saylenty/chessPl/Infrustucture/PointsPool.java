package com.gitlab.saylenty.chessPl.Infrustucture;

import java.util.HashMap;

/**
 * <p>
 * Created by Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 * </p>
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
