/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.Infrustucture;

import lombok.Data;
import lombok.ToString;

/**
 * Space class for the ChessGame
 */
@Data
@ToString(doNotUseGetters = true)
public final class Space implements Comparable<Space> {
    private final int x;
    private final int y;

    @Override
    public int compareTo(Space o) {
        int cmp = Integer.compare(this.x, o.x);
        return cmp == 0 ? Integer.compare(this.y, o.y) : cmp;
    }
}
