/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.logic;

@FunctionalInterface
public interface PlacementStrategy {

    /**
     * Start the algorithm
     * @return number of available permutations
     */
    int play ();
}
