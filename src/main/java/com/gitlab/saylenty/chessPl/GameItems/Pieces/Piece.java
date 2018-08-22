/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Pieces;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.Infrustucture.Space;
import com.gitlab.saylenty.chessPl.Infrustucture.utils.ColorPrinter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A chess game object (figure)
 */
public abstract class Piece {

    @Getter
    private final String name;

    @Getter
    private final Color color;

    @Getter
    private final String colorName;

    @Getter
    @Setter
    private ColorPrinter colorPrinter;

    /**
     * ChessBoard free spaces iterator
     */
    private Iterator<Space> iterator;

    /**
     * Current piece position
     */
    @Getter
    @Setter
    Space position;

    /**
     * A chess board the piece is associated with
     */
    @Getter
    @Setter
    ChessBoard chessBoard;

    /**
     * A piece captureZone
     */
    final Set<Space> captureZone;

    Piece(String name, Color color, Space position) {
        this.name = name;
        this.color = color;
        this.position = position;
        captureZone = new HashSet<>();
        colorPrinter = new ColorPrinter();
        colorName = color == null ? "" : colorPrinter.getColorName(color.getRGB());
    }

    /**
     * Moves a figure to an other free position
     *
     * @return indicates weather the moving was successful
     */
    public boolean move() {
        if (iterator == null) {
            iterator = chessBoard.getFreeSpaces().iterator();
        }
        if (iterator.hasNext()) {
            // have new available position (space) to move
            setPosition(iterator.next());
            // clear capture zone because of new piece place
            captureZone.clear();
            return true;
        }
        // don't have any free position (space) to move
        iterator = null;
        return false;
    }

    /**
     * @return kill zone of the piece
     */
    public Set<Space> getCaptureZone() {
        if (captureZone.isEmpty()) {
            // add current position as initial
            captureZone.add(this.getPosition());
            computeCaptureZone();
        }
        return captureZone;
    }

    /**
     * Calculates take zone for the piece based on it's current location on the board
     */
    protected abstract void computeCaptureZone();

    @Override
    public String toString() {
        return String.format("%s{name='%s', position={x='%d', y='%d'}, color='%s'}", getClass().getSimpleName(),
                getName(), position.getX(), position.getY(), colorName);
    }

    /**
     * Updates the piece captureZone with all positions that are above
     */
    final void up() {
        up(-1);
    }

    /**
     * Updates the piece captureZone with positions that are above or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #up()}.
     */
    final void up(int limit) {
        int x = position.getX();
        int y = position.getY();
        while (--y >= 0 && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the piece captureZone with all positions that are below
     */
    final void down() {
        down(-1);
    }

    /**
     * Updates the piece captureZone with positions that are below or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #down()}.
     */
    final void down(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (++y < chessBoardHeight && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the piece captureZone with all positions that are on the left
     */
    final void left() {
        left(-1);
    }

    /**
     * Updates the piece captureZone with positions that are on the left or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #left()}.
     */
    final void left(int limit) {
        int x = position.getX();
        int y = position.getY();
        while (--x >= 0 && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the captureZone of shapes with all the positions that are on the right
     */
    final void right() {
        right(-1);
    }

    /**
     * Updates the piece captureZone with positions that are on the right or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #right()}.
     */
    final void right(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardWidth = this.chessBoard.getWidth();
        while (++x < chessBoardWidth && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the captureZone of shapes with all the positions that are on the up left diagonal
     */
    final void upLeftDiagonal() {
        upLeftDiagonal(-1);
    }

    /**
     * Updates the piece captureZone with positions that are on the up left diagonal or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #upLeftDiagonal()}.
     */
    final void upLeftDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        while (--x >= 0 && --y >= 0 && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the captureZone of shapes with all the positions that are on the up right diagonal
     */
    final void upRightDiagonal() {
        upRightDiagonal(-1);
    }

    /**
     * Updates the piece captureZone with positions that are on the up right diagonal or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #upRightDiagonal()}.
     */
    final void upRightDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardWidth = this.chessBoard.getWidth();
        while (++x < chessBoardWidth && --y >= 0 && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the captureZone of shapes with all the positions that are on the down left diagonal
     */
    final void bottomLeftDiagonal() {
        bottomLeftDiagonal(-1);
    }

    /**
     * Updates the piece captureZone with positions that are on the down left diagonal or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #bottomLeftDiagonal()}.
     */
    final void bottomLeftDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (--x >= 0 && ++y < chessBoardHeight && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }

    /**
     * Updates the captureZone of shapes with all the positions that are on the down right diagonal
     */
    final void downRightDiagonal() {
        downRightDiagonal(-1);
    }

    /**
     * Updates the piece captureZone with positions that are on the down right diagonal or until limit is exceeded
     *
     * @param limit number of max points from the current piece position
     *              See also {@link #downRightDiagonal()}.
     */
    final void downRightDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardWidth = this.chessBoard.getWidth();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (++x < chessBoardWidth && ++y < chessBoardHeight && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }
}
