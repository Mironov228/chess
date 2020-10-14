package chess;

import chess.Piece.Piece;
import chess.Piece.PieceColor;

public enum GameStatus {
    START,
    CHECKMATE,
    PAT;

    private PieceColor color;

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }
}
