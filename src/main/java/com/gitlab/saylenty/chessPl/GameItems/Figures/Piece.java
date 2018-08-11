/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.Infrustucture.Space;
import com.gitlab.saylenty.chessPl.Infrustucture.utils.ColorPrinter;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A chess game object (figure)
 */
public abstract class Piece {

    private String name;
    private Color color;
    private ColorPrinter colorPrinter;
    private Iterator<Space> iterator;

    /**
     * Current piece position
     */
    Space position;

    /**
     * A chess board the piece is associated with
     */
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
    }

    /**
     * @return current color of the piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * Applies new color for a piece
     *
     * @param color new color of the piece
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return a name of the piece
     */
    public String getName() {
        return name;
    }

    /**
     * Applies new name for the piece
     *
     * @param name new piece name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Associates the piece with the chess board
     *
     * @param chessBoard a board associate the piece with
     */
    public void setBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean removeFromBoard(ChessBoard chessBoard) {
        boolean res = this.chessBoard.removePiece(this);
        if (res) {
            this.chessBoard = null;
        }
        return res;
    }

    /**
     * @return a piece position
     */
    public Space getPosition() {
        return position;
    }

    /**
     * Moves the piece to new position
     *
     * @param position a new piece position
     */
    public void setPosition(Space position) {
        if (!this.position.equals(position)) {
            this.position = position;
            captureZone.clear(); // TODO to move method
        }
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
            this.setPosition(iterator.next());
            return true;
        }
        iterator = null;
        return false;
    }

    /**
     * @return the killing zone of the piece
     */
    public Set<Space> getCaptureZone(){
        if (!captureZone.isEmpty()) {
            return captureZone;
        }
        // add current position as initial
        captureZone.add(this.getPosition());
        computeCaptureZone();
        return captureZone;
    }

    /**
     * Calculates take zone for the piece based on it's current location on the board
     */
    protected abstract void computeCaptureZone();

    @Override
    public String toString() {
        return String.format("Piece{name='%s', position={x = %d, y = %d}, color='%s'}", name,
                position.getX(), position.getY(), colorPrinter.getColorName(color.getRGB()));
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
        int y = position.getY();
        int x = position.getX();
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
        int y = position.getY();
        int x = position.getX();
        while (++y < this.chessBoard.getHeight() && limit-- != 0) {
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
        int y = position.getY();
        int x = position.getX();
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
        int y = position.getY();
        int x = position.getX();
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
        int y = position.getY();
        int x = position.getX();
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
        int y = position.getY();
        int x = position.getX();
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
        int y = position.getY();
        int x = position.getX();
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
        int y = position.getY();
        int x = position.getX();
        int chessBoardWidth = this.chessBoard.getWidth();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (++x < chessBoardWidth && ++y < chessBoardHeight && limit-- != 0) {
            Space p = new Space(x, y);
            captureZone.add(p);
        }
    }
}
