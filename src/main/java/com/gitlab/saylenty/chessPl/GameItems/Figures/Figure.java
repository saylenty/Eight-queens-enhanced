/**
 * Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.Infrustucture.Point;
import com.gitlab.saylenty.chessPl.Infrustucture.PointsPool;
import com.gitlab.saylenty.chessPl.Infrustucture.utils.ColorPrinter;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Figure {

    private String name;
    private Color color;
    private ColorPrinter colorPrinter;
    private Iterator<Point> iterator;
    private PointsPool pool;

    /**
     * Current figure position
     */
    Point position;

    /**
     * A ches board the figure is associated with
     */
    ChessBoard chessBoard;

    /**
     * A figure range
     */
    Set<Point> range;

    /**
     * A pool for points
     */
    public PointsPool getPool() {
        return pool;
    }

    Figure(String name, Color color, Point position) {
        this.name = name;
        this.color = color;
        this.position = position;
        range = new HashSet<>();
        pool = PointsPool.getInstance();
        colorPrinter = new ColorPrinter();
    }

    /**
     * @return current color of the figure
     */
    public Color getColor() {
        return color;
    }

    /**
     * Apply new color for a figure
     *
     * @param color new color of the figure
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return a name of the figure
     */
    public String getName() {
        return name;
    }

    /**
     * Apply new name for the figure
     *
     * @param name new figure name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Associates the figure with the chess board
     *
     * @param chessBoard a board associate the figure with
     */
    public void setBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean removeFromBoard(ChessBoard chessBoard) {
        return this.chessBoard.removeFigure(this);
    }

    /**
     * @return a figure position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Move the figure to new position
     *
     * @param position a new figure position
     */
    public void setPosition(Point position) {
        if (!this.position.equals(position)) {
            this.position = position;
            range.clear(); // TODO to move method
        }
    }

    /**
     * Moves a figure to an other free position
     *
     * @return indicates weather the moving was successful
     */
    public boolean move() {
        if (iterator == null) {
            iterator = chessBoard.getFreePoints().iterator();
        }
        if (iterator.hasNext()) {
            this.setPosition(iterator.next());
            return true;
        }
        iterator = null;
        return false;
    }

    /**
     * @return the killing zone of the figure
     */
    public abstract Set<Point> getRange();

    @Override
    public String toString() {
        return String.format("Figure{name='%s', position={x = %d, y = %d}, color='%s'}", name,
                position.getX(), position.getY(), colorPrinter.getColorName(color.getRGB()));
    }

    /**
     * Updates the figure range with all positions that are above
     */
    final void up() {
        up(-1);
    }

    /**
     * Updates the figure range with positions that are above or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #up()}.
     */
    final void up(int limit) {
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (y - i >= 0 && limit != 0) {
            Point p = pool.valueOf(x, y - i++);
            range.add(p);
            limit--;
        }
    }

    /**
     * Updates the figure range with all positions that are below
     */
    final void down() {
        down(-1);
    }

    /**
     * Updates the figure range with positions that are below or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #down()}.
     */
    final void down(int limit) {
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (y + i < this.chessBoard.getHeight() && limit != 0) {
            Point p = pool.valueOf(x, y + i++);
            range.add(p);
            limit--;
        }
    }

    /**
     * Updates the figure range with all positions that are on the left
     */
    final void left() {
        left(-1);
    }

    /**
     * Updates the figure range with positions that are on the left or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #left()}.
     */
    final void left(int limit) {
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (x - i >= 0 && limit != 0) {
            Point p = pool.valueOf(x - i++, y);
            range.add(p);
            limit--;
        }
    }

    /**
     * Updates the range of shapes with all the positions that are on the right
     */
    final void right() {
        right(-1);
    }

    /**
     * Updates the figure range with positions that are on the right or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #right()}.
     */
    final void right(int limit) {
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (x + i < this.chessBoard.getWidth() && limit != 0) {
            Point p = pool.valueOf(x + i++, y);
            range.add(p);
            limit--;
        }
    }

    /**
     * Updates the range of shapes with all the positions that are on the up left diagonal
     */
    final void upLeftDiagonal() {
        upLeftDiagonal(-1);
    }

    /**
     * Updates the figure range with positions that are on the up left diagonal or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #upLeftDiagonal()}.
     */
    final void upLeftDiagonal(int limit) {
        int y = position.getY();
        int x = position.getX();
        // diagonal left upwards
        int i = 1;
        while (x - i >= 0 && y - i >= 0 && limit != 0) {
            Point p = pool.valueOf(x - i, y - i);
            range.add(p);
            i++;
            limit--;
        }
    }

    /**
     * Updates the range of shapes with all the positions that are on the up right diagonal
     */
    final void upRightDiagonal() {
        upRightDiagonal(-1);
    }

    /**
     * Updates the figure range with positions that are on the up right diagonal or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #upRightDiagonal()}.
     */
    final void upRightDiagonal(int limit) {
        int y = position.getY();
        int x = position.getX();
        // diagonal right upwards
        int i = 1;
        while (x + i < this.chessBoard.getWidth() && y - i >= 0 && limit != 0) {
            Point p = pool.valueOf(x + i, y - i);
            range.add(p);
            i++;
            limit--;
        }
    }

    /**
     * Updates the range of shapes with all the positions that are on the down left diagonal
     */
    final void downLeftDiagonal() {
        downLeftDiagonal(-1);
    }

    /**
     * Updates the figure range with positions that are on the down left diagonal or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #downLeftDiagonal()}.
     */
    final void downLeftDiagonal(int limit) {
        int y = position.getY();
        int x = position.getX();
        // diagonal left downwards
        int i = 1;
        while (x - i >= 0 && y + i < this.chessBoard.getHeight() && limit != 0) {
            Point p = pool.valueOf(x - i, y + i);
            range.add(p);
            i++;
            limit--;
        }
    }

    /**
     * Updates the range of shapes with all the positions that are on the down right diagonal
     */
    final void downRightDiagonal() {
        downRightDiagonal(-1);
    }

    /**
     * Updates the figure range with positions that are on the down right diagonal or until limit is exceeded
     * @param limit number of max points from the current figure position
     * See also {@link #downRightDiagonal()}.
     */
    final void downRightDiagonal(int limit) {
        int y = position.getY();
        int x = position.getX();
        // diagonal right downwards
        int i = 1;
        while (x + i < this.chessBoard.getWidth() && y + i < this.chessBoard.getHeight() && limit != 0) {
            Point p = pool.valueOf(x + i, y + i);
            range.add(p);
            i++;
            limit--;
        }
    }
}
