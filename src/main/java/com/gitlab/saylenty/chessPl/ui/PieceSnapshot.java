package com.gitlab.saylenty.chessPl.ui;

import com.gitlab.saylenty.chessPl.gameItems.pieces.Piece;

public record PieceSnapshot(Class<? extends Piece> type, Piece.Color color, int x, int y) {}
