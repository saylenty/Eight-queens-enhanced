package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.SortedSet;


public class Bishop extends Figure {

    public Bishop(Color color) {
        this(color, new Point(0, 0));
    }

    public Bishop(Color color, Point position) {
        this("Bishop", color, position);
    }

    public Bishop(String name, Color color, Point position) {
        super(name, color, position);
    }

    @Override
    public SortedSet<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }
        range.add(this.getPosition()); // add current position as initial
        int x = this.position.getX();
        int y = this.position.getY();

        // diagonal left upwards
        int i = 1;
        while (x - i >= 0 && y - i >= 0) {
            Point p = pool.valueOf(x - i, y - i);
            range.add(p);
            i++;
        }

        // diagonal right upwards
        i = 1;
        while (x + i < this.chessBoard.getWidth() && y - i >= 0) {
            Point p = pool.valueOf(x + i, y - i);
            range.add(p);
            i++;
        }

        // diagonal right downwards
        i = 1;
        while (x + i < this.chessBoard.getWidth() && y + i < this.chessBoard.getHeight()) {
            Point p = pool.valueOf(x + i, y + i);
            range.add(p);
            i++;
        }

        // diagonal left downwards
        i = 1;
        while (x - i >= 0 && y + i < this.chessBoard.getHeight()) {
            Point p = pool.valueOf(x - i, y + i);
            range.add(p);
            i++;
        }
        return range;
    }
}
