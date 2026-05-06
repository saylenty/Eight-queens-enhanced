/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;

public final class BoardSquare implements Comparable<BoardSquare> {
    private final int x;
    private final int y;
    private static final Comparator<BoardSquare> spaceComparator =
            Comparator.comparingInt(BoardSquare::getX).thenComparingInt(BoardSquare::getY);

    private int timesLocked;

    public BoardSquare(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void lock() {
        ++timesLocked;
    }

    public void unlock() {
        if (--timesLocked < 0) {
            timesLocked = 0;
        }
    }

    public boolean isLocked() {
        return timesLocked > 0;
    }

    public boolean isUnlocked() {
        return timesLocked == 0;
    }

    @Override
    public int compareTo(@Nonnull BoardSquare o) {
        return spaceComparator.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardSquare that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "BoardSquare(x=" + x + ", y=" + y + ", timesLocked=" + timesLocked + ")";
    }
}
