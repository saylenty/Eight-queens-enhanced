/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.Set;

public class King extends Figure {

    public King() {
        this(null, new Point(0, 0));
    }

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
    public Set<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }

        range.add(this.getPosition()); // add current position as initial

        left(1);
        right(1);
        up(1);
        down(1);
        upLeftDiagonal(1);
        downRightDiagonal(1);
        upRightDiagonal(1);
        bottomLeftDiagonal(1);
        return range;
    }
}
