/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.Set;

public class Knight extends Figure {

    public Knight() {
        this(null, new Point(0, 0));
    }

    public Knight(Color color, Point position) {
        this("Knight", color, position);
    }

    public Knight(String name, Color color, Point position) {
        super(name, color, position);
    }

    public Knight(Color color) {
        this(color, new Point(0, 0));
    }

    @Override
    public Set<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }
        Point position = this.getPosition();
        range.add(position); // add current position as initial
        int x = position.getX();
        int y = position.getY();
        int chessBoardHeight = this.chessBoard.getHeight();
        int chessBoardWidth = this.chessBoard.getWidth();

        up(x, y);
        left(x, y, chessBoardHeight);
        bottom(x, y, chessBoardHeight, chessBoardWidth);
        right(x, y, chessBoardHeight, chessBoardWidth);
        return range;
    }

    private void up(int x, int y) {
        if (y - 2 >= 0 && x - 1 >= 0) {
            range.add(new Point(x - 1, y - 2));
        }
        if (y - 2 >= 0 && x + 1 >= 0) {
            range.add(new Point(x + 1, y - 2));
        }
    }

    private void bottom(int x, int y, int chessBoardHeight, int chessBoardWidth) {
        if (x - 1 >= 0 && y + 2 < chessBoardHeight) {
            range.add(new Point(x - 1, y + 2));
        }
        if (x + 1 < chessBoardWidth && y + 2 < chessBoardHeight) {
            range.add(new Point(x + 1, y + 2));
        }
    }

    private void left(int x, int y, int chessBoardHeight) {
        if (x - 2 >= 0 && y - 1 >= 0) {
            range.add(new Point(x - 2, y - 1));
        }
        if (x - 2 >= 0 && y + 1 < chessBoardHeight) {
            range.add(new Point(x - 2, y + 1));
        }
    }

    private void right(int x, int y, int chessBoardHeight, int chessBoardWidth) {
        if (x + 2 < chessBoardWidth && y + 1 < chessBoardHeight) {
            range.add(new Point(x + 2, y + 1));
        }
        if (x + 2 < chessBoardWidth && y - 1 >= 0) {
            range.add(new Point(x + 2, y - 1));
        }
    }
}
