package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.Infrustucture.Point;
import com.gitlab.saylenty.chessPl.Infrustucture.PointsPool;
import com.gitlab.saylenty.chessPl.Infrustucture.utils.ColorPrinter;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 * Created by Saylenty on 11-Apr-17.
 * Copyright (c) 2017
 * </p>
 */
public abstract class Figure{

    private String name;
    private Color color;
    private ColorPrinter colorPrinter;
    private Iterator<Point> iterator;
    Point position;
    ChessBoard chessBoard;
    Set<Point> range;
    PointsPool pool;

    Figure(String name, Color color, Point position) {
        this.name = name;
        this.color = color;
        this.position = position;
        range = new HashSet<>();
        pool = PointsPool.getInstance();
        colorPrinter = new ColorPrinter();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean removeFromBoard(ChessBoard chessBoard) {
        return this.chessBoard.removeFigure(this);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        if (!this.position.equals(position)){
            this.position = position;
            range.clear(); // TODO to move method
        }
    }

    public boolean move() {
        if (iterator == null){
            iterator = chessBoard.getFreePoints().iterator();
        }
        if (iterator.hasNext()) {
            this.setPosition(iterator.next());
            return true;
        }
        iterator = null;
        return false;
    }

    public abstract Set<Point> getRange();

    @Override
    public String toString() {
        return String.format("Figure{name='%s', position={x = %d, y = %d}, color='%s'}", name,
                position.getX(), position.getY(), colorPrinter.getColorName(color.getRGB()));
    }

    final void up() {
        up(-1);
    }

    final void up(int limit){
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (y - i >= 0 && limit != 0) {
            Point p = pool.valueOf(x, y - i++);
            range.add(p);
            limit--;
        }
    }

    final void down() {
        down(-1);
    }

    final void down(int limit){
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (y + i < this.chessBoard.getHeight() && limit != 0) {
            Point p = pool.valueOf(x, y + i++);
            range.add(p);
            limit--;
        }
    }

    final void left() {
        left(-1);
    }

    final void left(int limit){
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (x - i >= 0 && limit != 0) {
            Point p = pool.valueOf(x - i++, y);
            range.add(p);
            limit--;
        }
    }

    final void right() {
        right(-1);
    }

    final void right(int limit){
        int y = position.getY();
        int x = position.getX();
        int i = 1;
        while (x + i < this.chessBoard.getWidth() && limit != 0) {
            Point p = pool.valueOf(x + i++, y);
            range.add(p);
            limit--;
        }
    }

    final void upLeftDiagonal() {
        upLeftDiagonal(-1);
    }

    final void upLeftDiagonal(int limit){
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

    final void upRightDiagonal() {
        upRightDiagonal(-1);
    }

    final void upRightDiagonal(int limit){
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

    final void downLeftDiagonal() {
        downLeftDiagonal(-1);
    }

    final void downLeftDiagonal(int limit){
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

    final void downRightDiagonal() {
        downRightDiagonal(-1);
    }

    final void downRightDiagonal(int limit){
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
