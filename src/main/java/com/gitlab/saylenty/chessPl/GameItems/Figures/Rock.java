/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.Infrustucture.Point;

import java.awt.*;
import java.util.Set;

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
    public Set<Point> getRange() {
        if (!range.isEmpty()) {
            return range;
        }
        range.add(this.position); // add current position as initial

        up();
        down();
        left();
        right();

        return range;
    }
}
