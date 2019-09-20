/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.Space;

public class Knight extends Piece {

    public Knight(Color color) {
        super(null, "Knight", color);
    }

    public Knight(Color color, Space position) {
        super("Knight", color, null, position);
    }

    public Knight(String name, Color color, Space position) {
        super(name, color, null, position);
    }

    @Override
    protected void computeCaptureZone() {
        int x = position.getX();
        int y = position.getY();
        int chessBoardHeight = this.chessBoard.getHeight();
        int chessBoardWidth = this.chessBoard.getWidth();

        up(x, y);
        left(x, y, chessBoardHeight);
        bottom(x, y, chessBoardHeight, chessBoardWidth);
        right(x, y, chessBoardHeight, chessBoardWidth);
    }

    private void up(int x, int y) {
        y -= 2;
        if (y >= 0) {
            int a = x - 1;
            if (a >= 0) {
                captureZone.add(new Space(a, y));
            }
            int b = x + 1;
            if (b >= 0) {
                captureZone.add(new Space(b, y));
            }
        }
    }

    private void bottom(int x, int y, int chessBoardHeight, int chessBoardWidth) {
        y += 2;
        if (y < chessBoardHeight) {
            int a = x - 1;
            if (a >= 0) {
                captureZone.add(new Space(a, y));
            }
            int b = x + 1;
            if (b < chessBoardWidth) {
                captureZone.add(new Space(b, y));
            }
        }
    }

    private void left(int x, int y, int chessBoardHeight) {
        x -= 2;
        if (x >= 0) {
            int a = y - 1;
            if (a >= 0) {
                captureZone.add(new Space(x, a));
            }
            int b = y + 1;
            if (b < chessBoardHeight) {
                captureZone.add(new Space(x, b));
            }
        }
    }

    private void right(int x, int y, int chessBoardHeight, int chessBoardWidth) {
        x += 2;
        if (x < chessBoardWidth) {
            int a = y + 1;
            if (a < chessBoardHeight) {
                captureZone.add(new Space(x, a));
            }
            int b = y - 1;
            if (b >= 0) {
                captureZone.add(new Space(x, b));
            }
        }
    }
}
