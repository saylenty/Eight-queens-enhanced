/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.util.Comparator;

/**
 * Space class for the ChessGame
 */
@Data
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(of = {"x", "y"})
public final class BoardSquare implements Comparable<BoardSquare> {
    private final int x;
    private final int y;
    private static final Comparator<BoardSquare> spaceComparator =
            Comparator.comparingInt(BoardSquare::getX).thenComparingInt(BoardSquare::getY);

    private int timesLocked;

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
}
