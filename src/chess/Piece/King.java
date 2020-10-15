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

        if (game.ks == Game.KingStatus.NOTHING)
        {

           if (color==PieceColor.WHITE)
            {
                if (game.castles[0]) {
                    Square  tmp1=game.get(new Coord(1, 7)),
                            tmp2=game.get(new Coord(2, 7)),
                            tmp3=game.get(new Coord(3, 7));
                    if (tmp1.get()==null&&tmp2.get()==null&&tmp3.get()==null)
                        possibleMoves.add(tmp2);
                }
                if (game.castles[1]) {
                    Square  tmp1=game.get(new Coord(5, 7)),
                            tmp2=game.get(new Coord(6, 7));
                    if (tmp1.get()==null&&tmp2.get()==null)
                        possibleMoves.add(tmp2);
                }
            }
            else {
                if (game.castles[2]) {
                    Square  tmp1=game.get(new Coord(1, 0)),
                            tmp2=game.get(new Coord(2, 0)),
                            tmp3=game.get(new Coord(3, 0));
                    if (tmp1.get()==null&&tmp2.get()==null&&tmp3.get()==null)
                        possibleMoves.add(tmp2);
                }
                if (game.castles[3]) {
                    Square  tmp1=game.get(new Coord(5, 0)),
                            tmp2=game.get(new Coord(6, 0));
                    if (tmp1.get()==null&&tmp2.get()==null)
                        possibleMoves.add(tmp2);
                }
            }
        }
        return possibleMoves;
    }
}
