package com.gitlab.saylenty.chessPl.Logic;

/**
 * <p>
 * Created by Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 * </p>
 */
public class ChessGame {

    public int start(PlacementStrategy placementStrategy){
        return placementStrategy.play();
    }
}
