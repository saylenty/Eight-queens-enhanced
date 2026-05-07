/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.gameItems.pieces;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.generator.RangeGenerationStrategy;

import java.util.*;
import java.util.stream.Stream;

public abstract sealed class Piece permits Bishop, King, Knight, Queen, Rook {

    public enum Color {
        WHITE, BLACK
    }

    private final String name;
    private final Color color;
    private final RangeGenerationStrategy captureZoneGenerator;

    private Iterator<BoardSquare> iterator;

    BoardSquare position;
    ChessBoard chessBoard;
    final Set<BoardSquare> captureZone;

    Piece(String name, Color color, RangeGenerationStrategy captureZoneGenerator) {
        this.name = name;
        this.color = color;
        this.captureZoneGenerator = captureZoneGenerator;
        captureZone = new HashSet<>();
    }

    Piece(String name, Color color, RangeGenerationStrategy captureZoneGenerator, BoardSquare position) {
        this(name, color, captureZoneGenerator);
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

    protected void computeCaptureZone() {
        captureZoneGenerator.generate(position, chessBoard.getHeight(), chessBoard.getWidth())
                .forEach(captureZone::add);
    }

    @Override
    public String toString() {
        return String.format("%s{name='%s', position={x='%d', y='%d'}, color='%s'}", getClass().getSimpleName(),
                getName(), position.getX(), position.getY(), color);
    }
}
