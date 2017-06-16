package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.SortedSet;


public class King extends Figure {

    public King(Color color, Point position) {
        this("King", color, position);
    }

    public King(String name, Color color, Point position) {
        super(name, color, position);
    }

    public King(Color color) {
        this(color, new Point(0, 0));
    }

    @Override
    public SortedSet<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }

        range.add(this.getPosition()); // add current position as initial
        int x = this.position.getX();
        int y = this.position.getY();

        // left
        if (x - 1 >= 0) {
            range.add(pool.valueOf(x - 1, y));
        }
        // up
        if (y - 1 >= 0) {
            range.add(pool.valueOf(x, y - 1));
        }

        // left and up (diagonal)
        if (x - 1 >= 0 && y - 1 >= 0) {
            range.add(pool.valueOf(x - 1, y - 1));
        }

        // right
        if (x + 1 < this.chessBoard.getWidth()) {
            range.add(pool.valueOf(x + 1, y));
        }
        // right and up (diagonal)
        if (x + 1 < this.chessBoard.getWidth() && y - 1 >= 0) {
            range.add(pool.valueOf(x + 1, y - 1));
        }

        // down
        if (y + 1 < this.chessBoard.getHeight()) {
            range.add(pool.valueOf(x, y + 1));
        }
        // down and right (diagonal)
        if (x + 1 < this.chessBoard.getWidth() && y + 1 < this.chessBoard.getHeight()) {
            range.add(pool.valueOf(x + 1, y + 1));
        }

        //left and down (diagonal)
        if (x - 1 >= 0 && y + 1 < this.chessBoard.getHeight()) {
            range.add(pool.valueOf(x - 1, y + 1));
        }
        return range;
    }
}
