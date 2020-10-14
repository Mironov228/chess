package chess.Piece;

import chess.Coord;
import chess.Game;
import chess.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public List<Square> getPossibleMoves(Game game) {
        List<Square> possibleMoves = new ArrayList<>();

        int[][] pseudoMoves = {
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1}
        };

        for (int[] o : pseudoMoves) {
            Square tmp = game.get(new Coord(square.coord.x+o[0], square.coord.y+o[1]));
            if (tmp != null) {
                if (tmp.get() == null)
                    possibleMoves.add(tmp);
                else if (tmp.get().color != color)
                    possibleMoves.add(tmp);
            }

        }

        return possibleMoves;
    }
}
