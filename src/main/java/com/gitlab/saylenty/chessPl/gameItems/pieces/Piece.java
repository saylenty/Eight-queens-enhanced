/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;

/**
 * A chess game object (figure)
 */
public abstract class Piece {

    public enum Color {
        WHITE, BLACK
    }

    @Getter
    private final String name;

    @Getter
    private final Color color;

    /**
     * ChessBoard free spaces iterator
     */
    private Iterator<BoardSquare> iterator;

    /**
     * Current piece position
     */
    @Getter
    BoardSquare position;

    /**
     * Changes the figure position to new one and clears the capture zone in case new position is set
     *
     * @param position new potion to locate the figure
     * @implNote if {@code position} equals current location nothing is done
     */
    public void setPosition(BoardSquare position) {
        if (!Objects.equals(this.position, position)) {
            this.position = position;
            // clear capture zone because the figure location on the board is changed
            this.captureZone.clear();
        }
    }

    /**
     * A chess board the piece is associated with
     */
    @Getter
    @Setter
    ChessBoard chessBoard;

    /**
     * A piece captureZone
     */
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

    /**
     * Moves a figure to another free position
     *
     * @return indicates whether the moving was successful
     */
    public boolean move() {
        if (iterator == null) {
            iterator = getNextAppropriateMovePositions().iterator();
        }
        if (iterator.hasNext()) {
            // have new available position (space) to move
            setPosition(iterator.next());
            return true;
        }
        // don't have any free position (space) to move
        iterator = null;
        return false;
    }

    /**
     * Returns all possible spaces that this figure could move to
     * but further than the furthest figure of the same type on the board
     *
     * @return Next possible locations at the board
     */
    private Stream<BoardSquare> getNextAppropriateMovePositions() {
        Stream<BoardSquare> freeZone = chessBoard.getFreeZone();
        Optional<BoardSquare> max = chessBoard.getBoardPieces()
                .filter(p -> p.getClass().equals(this.getClass()))
                .map(Piece::getPosition)
                .max(BoardSquare::compareTo);
        return max.map(e -> freeZone.filter(space -> space.compareTo(e) > 0)).orElse(freeZone);
    }

    /**
     * @return kill zone of the piece
     */
    public Set<BoardSquare> getCaptureZone() {
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
                getName(), position.getX(), position.getY(), color);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
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
            BoardSquare p = new BoardSquare(x, y);
            captureZone.add(p);
        }
    }
}
