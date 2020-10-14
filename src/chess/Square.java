package chess;

import chess.Piece.Piece;

public class Square {
    private Piece piece;
    public Coord coord;
    public Square(Coord coord, Piece piece) {
        this.coord = coord;
        this.piece = piece;
        if (piece != null)
            piece.square = this;
    }

    public Square(Square square) {
        coord = square.coord;
        piece = square.piece;
        if (piece != null)
            piece.square = this;
    }

    public void remove() {
        piece = null;
    }

    public Piece get() {
        return piece;
    }

    public void set(Piece piece) {
        this.piece = piece;
        if (piece!=null)
            piece.square = this;
    }

    @Override
    public String toString() {
        return coord.x + " " + coord.y + " " + (piece==null? "" : String.valueOf(piece.type));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Square) {
            Coord crd = ((Square) obj).coord;
            return coord.x == crd.x && coord.y == crd.y;
        }
        return super.equals(obj);
    }
}
