package com.gitlab.saylenty.chessPl.gameItems.generator;

public class BiDiagonalGenerator extends CompositeGenerator {

    public BiDiagonalGenerator() {
        super(new BottomLeftDiagonalGenerator(), new BottomRightDiagonalGenerator(),
                new UpLeftDiagonalGenerator(), new UpRightDiagonalGenerator());
    }
}
