/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.Space;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A chess game object (figure)
 */
public abstract class Piece {

    public enum Color {
        WHITE, BLACK
    }

    final RangeGenerationStrategy captureZoneGenerator;

    @Getter
    private final String name;

    @Getter
    private final Color color;

    /**
     * ChessBoard free spaces iterator
     */
    private Iterator<Space> iterator;

    /**
     * Current piece position
     */
    @Getter
    Space position;

    /**
     * Changes the figure position to new one and clears the capture zone in case new position is set
     *
     * @param position new potion to locate the figure
     * @implNote if {@code position} equals current location nothing is done
     */
    public void setPosition(Space position) {
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
    Set<Space> captureZone;

    Piece(String name, Color color, RangeGenerationStrategy captureZoneGenerator, Space position) {
        this(captureZoneGenerator, name, color);
        this.position = position;
    }

    Piece(RangeGenerationStrategy captureZoneGenerator, String name, Color color) {
        this.captureZoneGenerator = captureZoneGenerator;
        this.name = name;
        this.color = color;
        captureZone = new HashSet<>();
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
    private Stream<Space> getNextAppropriateMovePositions() {
        Stream<Space> freeZone = chessBoard.getFreeZone();
        Optional<Space> max = chessBoard.getBoardPieces()
                .filter(p -> p.getClass().equals(this.getClass()))
                .map(Piece::getPosition)
                .max(Space::compareTo);
        return max.map(e -> freeZone.filter(space -> space.compareTo(e) > 0)).orElse(freeZone);
    }

    /**
     * @return kill zone of the piece
     */
    public Set<Space> getCaptureZone() {
        if (captureZone.isEmpty()) {
            // add current position as initial
            computeCaptureZone();
            captureZone.add(this.getPosition());
        }
        return captureZone;
    }

    /**
     * Calculates take zone for the piece based on it's current location on the board
     */
    protected void computeCaptureZone() {
        captureZone = captureZoneGenerator.generate(this.position, this.chessBoard.getHeight(),
                this.chessBoard.getWidth()).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return String.format("%s{name='%s', position={x='%d', y='%d'}, color='%s'}", getClass().getSimpleName(),
                getName(), position.getX(), position.getY(), color);
    }
}
