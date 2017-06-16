package com.gitlab.saylenty.chessPl.Logic;

public class ChessGame {

    public int start(PlacementStrategy placementStrategy){
        return placementStrategy.play();
    }
}
