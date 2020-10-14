package chess.Piece;

import chess.Coord;
import chess.Game;
import chess.Ranges;
import chess.Square;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public List<Square> getPossibleMoves(Game game) {
        List<Square> possibleMoves = new ArrayList<>();
        List<Coord> coordsAround = Ranges.getCoordsAroundSquare(square.coord);

        for (Coord around : coordsAround) {
            if (game.get(around).get() == null || game.get(around).get().color != color)
                possibleMoves.add(game.get(around));
        }
        return possibleMoves;
    }
}
