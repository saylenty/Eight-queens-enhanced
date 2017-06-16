package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.SortedSet;


public class Rock extends Figure {
    public Rock(Color color, Point position) {
        this("Rock", color, position);
    }

    public Rock(String name, Color color, Point position) {
        super(name, color, position);
    }

    public Rock(Color color) {
        this(color, new Point(0, 0));
    }

    @Override
    public SortedSet<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }
        range.add(this.position); // add current position as initial
        int x = this.position.getX();
        int y = this.position.getY();

        // look upwards
        int i = 1;
        while (y - i >= 0) {
            Point p = pool.valueOf(x, y - i);
            range.add(p);
            i++;
        }

        // look downwards
        i = 1;
        while (y + i < this.chessBoard.getHeight()) {
            Point p = pool.valueOf(x, y + i);
            range.add(p);
            i++;
        }

        // look leftwards
        i = 1;
        while (x - i >= 0) {
            Point p = pool.valueOf(x - i, y);
            range.add(p);
            i++;
        }

        // look rightwards
        i = 1;
        while (x + i < this.chessBoard.getWidth()) {
            Point p = pool.valueOf(x + i, y);
            range.add(p);
            i++;
        }
        return range;
    }
}
