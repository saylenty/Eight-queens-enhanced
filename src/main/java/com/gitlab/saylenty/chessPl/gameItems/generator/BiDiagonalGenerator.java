package com.gitlab.saylenty.chessPl.gameItems.generator;

public class BiDiagonalGenerator extends CompositeGenerator {

    public BiDiagonalGenerator() {
        super(new UpLeftDiagonalGenerator(), new UpRightDiagonalGenerator(),
                new BottomLeftDiagonalGenerator(), new BottomRightDiagonalGenerator());
    }

    public BiDiagonalGenerator(long limit) {
        super(new UpLeftDiagonalGenerator(limit), new UpRightDiagonalGenerator(limit),
                new BottomLeftDiagonalGenerator(limit), new BottomRightDiagonalGenerator(limit));
    }
}
