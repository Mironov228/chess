package chess.Piece;

import chess.Coord;
import chess.Game;
import chess.Square;

import java.util.List;

public abstract class Piece
{
    public Square square;
    public PieceType type;
    public PieceColor color;
    public Piece(PieceColor color) {
        this.color = color;
        String className = this.getClass().getSimpleName().toUpperCase();
        String stringType = color.toString()+"_"+className;
        type = PieceType.valueOf(stringType);
    }

    public abstract List<Square> getPossibleMoves(Game game);
}
