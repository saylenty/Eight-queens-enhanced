package com.gitlab.saylenty.chessPl.ui;

import com.gitlab.saylenty.chessPl.gameItems.ChessBoard;
import com.gitlab.saylenty.chessPl.gameItems.BoardSquare;
import com.gitlab.saylenty.chessPl.gameItems.GamePiecesFactory;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;
import com.gitlab.saylenty.chessPl.gameItems.pieces.Queen;
import com.gitlab.saylenty.chessPl.logic.BFRecursiveStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller for the chess board UI loaded from FXML.
 */
public class ChessBoardController {

    private static final int SIZE = 8;

    @FXML
    private GridPane boardGrid;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button dangerButton;

    private List<List<PieceSnapshot>> solutions;
    private int current;
    private boolean showAllDanger;
    private final Set<PieceSnapshot> activeDanger = new HashSet<>();

    @FXML
    private void initialize() {
        collectSolutions();
        drawBoard();
        showSolution(0);
    }

    @FXML
    private void handlePrev() {
        showSolution(current - 1);
    }

    @FXML
    private void handleNext() {
        showSolution(current + 1);
    }

    @FXML
    private void handleToggleDanger() {
        showAllDanger = !showAllDanger;
        renderCurrent();
    }

    private void drawBoard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                StackPane cell = new StackPane();
                Rectangle r = new Rectangle(40, 40);
                r.setFill((x + y) % 2 == 0 ? Color.BEIGE : Color.BROWN);
                cell.getChildren().add(r);
                boardGrid.add(cell, x, y);
            }
        }
    }

    private void collectSolutions() {
        ChessBoard board = new ChessBoard(SIZE, SIZE);
        GamePiecesFactory factory = new GamePiecesFactory();
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            pieces.add(factory.createPiece(Queen.class, Piece.Color.BLACK, board));
        }
        List<List<PieceSnapshot>> list = new ArrayList<>();
        BFRecursiveStrategy strategy = new BFRecursiveStrategy(board, pieces, stream -> {
            list.add(stream.map(p -> new PieceSnapshot(p.getClass(), p.getColor(),
                    p.getPosition().getX(), p.getPosition().getY()))
                    .collect(Collectors.toList()));
        });
        strategy.play();
        solutions = list;
    }

    private void showSolution(int index) {
        if (index < 0 || index >= solutions.size()) {
            return;
        }
        current = index;
        activeDanger.clear();
        renderCurrent();
    }

    private void renderCurrent() {
        List<PieceSnapshot> solution = solutions.get(current);
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                StackPane cell = getCell(x, y);
                Rectangle r = (Rectangle) cell.getChildren().get(0);
                r.setStroke(null);
                if (cell.getChildren().size() > 1) cell.getChildren().remove(1);
            }
        }
        for (PieceSnapshot ps : solution) {
            StackPane cell = getCell(ps.x(), ps.y());
            Label lbl = new Label("Q");
            cell.getChildren().add(lbl);
            cell.setOnMouseClicked(e -> togglePiece(ps));
        }
        if (showAllDanger) {
            solution.forEach(activeDanger::add);
        }
        for (PieceSnapshot ps : activeDanger) {
            highlightDanger(ps);
        }
    }

    private StackPane getCell(int x, int y) {
        for (javafx.scene.Node node : boardGrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (StackPane) node;
            }
        }
        return null;
    }

    private void togglePiece(PieceSnapshot ps) {
        if (activeDanger.contains(ps)) {
            activeDanger.remove(ps);
        } else {
            activeDanger.add(ps);
        }
        renderCurrent();
    }

    private void highlightDanger(PieceSnapshot ps) {
        ChessBoard board = new ChessBoard(SIZE, SIZE);
        GamePiecesFactory factory = new GamePiecesFactory();
        Piece piece = factory.createPiece((Class<? extends Piece>) ps.type(), ps.color(), board);
        piece.setPosition(new BoardSquare(ps.x(), ps.y()));
        for (BoardSquare square : piece.getCaptureZone()) {
            StackPane cell = getCell(square.getX(), square.getY());
            Rectangle r = (Rectangle) cell.getChildren().get(0);
            r.setStroke(Color.RED);
        }
    }
}
