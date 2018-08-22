/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.logic;

public class ChessGame {

    /**
     * Starts the game with desired placement strategy
     * @param placementStrategy strategy for figures placement
     * @return a number of available figures permutations
     */
    public int start(PlacementStrategy placementStrategy){
        return placementStrategy.play();
    }
}
