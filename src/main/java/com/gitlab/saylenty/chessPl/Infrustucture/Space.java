/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Infrustucture;

/**
 * Space class for the ChessGame
 */
public final class Space implements Comparable<Space> {
    private final int x;
    private final int y;

    public Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Space space = (Space) o;
        return x == space.x && y == space.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public int compareTo(Space o) {
        int cmp = Integer.compare(this.x, x);
        return cmp == 0 ? Integer.compare(this.y, y) : cmp;
    }
}
