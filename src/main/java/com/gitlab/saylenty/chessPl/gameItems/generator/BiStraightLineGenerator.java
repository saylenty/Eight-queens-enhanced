package com.gitlab.saylenty.chessPl.gameItems.generator;

public class BiStraightLineGenerator extends CompositeGenerator {

    public BiStraightLineGenerator() {
        super(new UpwardsGenerator(), new DownwardsGenerator(), new LeftwardsGenerator(), new RightwardsGenerator());
    }
}
