package com.gitlab.saylenty.chessPl.GameItems.Figures;

import com.gitlab.saylenty.chessPl.GameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.Infrustucture.Point;
import com.gitlab.saylenty.chessPl.Infrustucture.PointsPool;
import com.gitlab.saylenty.chessPl.Infrustucture.utils.ColorPrinter;

import java.awt.Color;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;


public abstract class Figure {

    private String name;
    private Color color;
    private ColorPrinter colorPrinter;
    Point position;
    ChessBoard chessBoard;
    SortedSet<Point> range;
    PointsPool pool;

    Figure(String name, Color color, Point position) {
        this.name = name;
        this.color = color;
        this.position = position;
        range = new TreeSet<>();
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

    private Iterator<Point> iterator;

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

    public abstract SortedSet<Point> getRange();

    @Override
    public String toString() {
        return String.format("Figure{name='%s', position={x = %d, y = %d}, color='%s'}", name,
                position.getX(), position.getY(), colorPrinter.getColorName(color.getRGB()));
    }
}
