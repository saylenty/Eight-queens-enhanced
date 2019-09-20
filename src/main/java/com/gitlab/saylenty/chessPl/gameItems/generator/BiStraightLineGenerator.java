package com.gitlab.saylenty.chessPl.gameItems.generator;

public class BiStraightLineGenerator extends CompositeGenerator {

    public BiStraightLineGenerator() {
        super(new UpwardsGenerator(), new DownwardsGenerator(), new LeftwardsGenerator(), new RightwardsGenerator());
    }

    public BiStraightLineGenerator(long limit) {
        super(new UpwardsGenerator(limit), new DownwardsGenerator(limit), new LeftwardsGenerator(limit), new RightwardsGenerator(limit));
    }
}
