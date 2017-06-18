/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.Set;

public class Knight extends Figure {
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
        range.add(this.getPosition()); // add current position as initial
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        // up
        if (y - 2 >= 0 && x - 1 >= 0) {
            range.add(getPool().valueOf(x - 1, y - 2));
        }
        if (y - 2 >= 0 && x + 1 >= 0) {
            range.add(getPool().valueOf(x + 1, y - 2));
        }

        // left
        if (x - 2 >= 0 && y - 1 >= 0) {
            range.add(getPool().valueOf(x - 2, y - 1));
        }
        if (x - 2 >= 0 && y + 1 < this.chessBoard.getHeight()) {
            range.add(getPool().valueOf(x - 2, y + 1));
        }

        // bottom
        if (x - 1 >= 0 && y + 2 < this.chessBoard.getHeight()) {
            range.add(getPool().valueOf(x - 1, y + 2));
        }
        if (x + 1 < this.chessBoard.getWidth() && y + 2 < this.chessBoard.getHeight()) {
            range.add(getPool().valueOf(x + 1, y + 2));
        }

        // right
        if (x + 2 < this.chessBoard.getWidth() && y + 1 < this.chessBoard.getHeight()) {
            range.add(getPool().valueOf(x + 2, y + 1));
        }
        if (x + 2 < this.chessBoard.getWidth() && y - 1 >= 0) {
            range.add(getPool().valueOf(x + 2, y - 1));
        }
        return range;
    }
}
