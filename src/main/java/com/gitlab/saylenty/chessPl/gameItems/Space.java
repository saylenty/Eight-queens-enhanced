/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems;

import lombok.Data;
import lombok.ToString;

import java.util.Comparator;

/**
 * Space class for the ChessGame
 */
@Data
@ToString(doNotUseGetters = true)
public final class Space implements Comparable<Space> {
    private final int x;
    private final int y;
    private static final Comparator<Space> spaceComparator =
            Comparator.comparingInt(Space::getX).thenComparingInt(Space::getY);

    @Override
    public int compareTo(Space o) {
        return spaceComparator.compare(this, o);
    }
}
