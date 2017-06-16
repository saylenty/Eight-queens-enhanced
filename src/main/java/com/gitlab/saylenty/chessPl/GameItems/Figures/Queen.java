package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.SortedSet;


public class Queen extends Figure {
    public Queen(Color color, Point position) {
        this("Queen", color, position);
    }

    public Queen(String name, Color color, Point position) {
        super(name, color, position);
    }

    public Queen(Color color) {
        this(color, new Point(0, 0));
    }

    @Override
    public SortedSet<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }
        range.add(this.getPosition()); // add current position as initial
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

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

        // diagonal left upwards
        i = 1;
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
