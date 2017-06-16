package com.gitlab.saylenty.chessPl.Infrustucture;


public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public int compareTo(Point o) {
        if (x < o.getX()) {
            return -1;
        }
        if (x > o.getX()) {
            return 1;
        }
        if (x == o.getX() && y < o.getY()) {
            return -1;
        }
        if (x == o.getX() && y > o.getY()) {
            return 1;
        }
        return 0;
    }
}
