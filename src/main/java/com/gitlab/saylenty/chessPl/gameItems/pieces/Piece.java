/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;

import java.util.*;
import java.util.stream.Stream;

public abstract sealed class Piece permits Bishop, King, Knight, Queen, Rock {

    public enum Color {
        WHITE, BLACK
    }

    private final String name;
    private final Color color;

    private Iterator<BoardSquare> iterator;

    BoardSquare position;
    ChessBoard chessBoard;
    final Set<BoardSquare> captureZone;

    Piece(String name, Color color) {
        this.name = name;
        this.color = color;
        captureZone = new HashSet<>();
    }

    Piece(String name, Color color, BoardSquare position) {
        this(name, color);
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public BoardSquare getPosition() {
        return position;
    }

    public void setPosition(BoardSquare position) {
        if (!Objects.equals(this.position, position)) {
            this.position = position;
            this.captureZone.clear();
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean move() {
        if (iterator == null) {
            iterator = getNextAppropriateMovePositions().iterator();
        }
        if (iterator.hasNext()) {
            setPosition(iterator.next());
            return true;
        }
        iterator = null;
        return false;
    }

    private Stream<BoardSquare> getNextAppropriateMovePositions() {
        Stream<BoardSquare> freeZone = chessBoard.getFreeZone();
        Optional<BoardSquare> max = chessBoard.getBoardPieces()
                .filter(p -> p.getClass().equals(this.getClass()))
                .map(Piece::getPosition)
                .max(BoardSquare::compareTo);
        return max.map(e -> freeZone.filter(space -> space.compareTo(e) > 0)).orElse(freeZone);
    }

    public Set<BoardSquare> getCaptureZone() {
        if (captureZone.isEmpty()) {
            captureZone.add(this.getPosition());
            computeCaptureZone();
        }
        return captureZone;
    }

    protected abstract void computeCaptureZone();

    @Override
    public String toString() {
        return String.format("%s{name='%s', position={x='%d', y='%d'}, color='%s'}", getClass().getSimpleName(),
                getName(), position.getX(), position.getY(), color);
    }

    final void up() {
        up(-1);
    }

    final void up(int limit) {
        int x = position.getX();
        int y = position.getY();
        while (--y >= 0 && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void down() {
        down(-1);
    }

    final void down(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (++y < chessBoardHeight && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void left() {
        left(-1);
    }

    final void left(int limit) {
        int x = position.getX();
        int y = position.getY();
        while (--x >= 0 && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void right() {
        right(-1);
    }

    final void right(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardWidth = this.chessBoard.getWidth();
        while (++x < chessBoardWidth && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void upLeftDiagonal() {
        upLeftDiagonal(-1);
    }

    final void upLeftDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        while (--x >= 0 && --y >= 0 && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void upRightDiagonal() {
        upRightDiagonal(-1);
    }

    final void upRightDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardWidth = this.chessBoard.getWidth();
        while (++x < chessBoardWidth && --y >= 0 && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void bottomLeftDiagonal() {
        bottomLeftDiagonal(-1);
    }

    final void bottomLeftDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (--x >= 0 && ++y < chessBoardHeight && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }

    final void downRightDiagonal() {
        downRightDiagonal(-1);
    }

    final void downRightDiagonal(int limit) {
        int x = position.getX();
        int y = position.getY();
        int chessBoardWidth = this.chessBoard.getWidth();
        int chessBoardHeight = this.chessBoard.getHeight();
        while (++x < chessBoardWidth && ++y < chessBoardHeight && limit-- != 0) {
            captureZone.add(new BoardSquare(x, y));
        }
    }
}
