package chess.Piece;

import chess.Coord;
import chess.Game;
import chess.Square;

import java.util.List;
import java.util.ArrayList;
public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public List<Square> getPossibleMoves(Game game) {
        List<Square> possibleMoves = new ArrayList<>();
        if (color == PieceColor.WHITE) {
            if (square.coord.y == 6) {
                Square move = game.get(new Coord(square.coord.x, 4));
                Square tmp = game.get(new Coord(square.coord.x, 5));
                boolean moveIsClear = false;
                boolean tmpIsClear = false;
                if (move != null) {
                    if (move.get() == null)
                        moveIsClear = true;
                }
                else
                    moveIsClear = false;
                if (tmp != null) {
                    if (tmp.get() == null)
                        tmpIsClear = true;
                } else
                    moveIsClear = false;
                if (moveIsClear && tmpIsClear)
                    possibleMoves.add(move);
            }

            Square move = game.get(new Coord(square.coord.x, square.coord.y-1));
            boolean moveIsClear = false;

            if (move != null)
                if (move.get() == null)
                    moveIsClear = true;
            if (moveIsClear)
                possibleMoves.add(move);

            move = game.get(new Coord(square.coord.x-1, square.coord.y-1));

            if (move != null)
                if (move.get() != null)
                    if (move.get().color != color){
                        possibleMoves.add(move);
                    }
            move = game.get(new Coord(square.coord.x+1, square.coord.y-1));
            if (move != null) {
                if (move.get() != null) {
                    if (move.get().color != color) {
                        possibleMoves.add(move);
                    }
                }
            }
        } else {
            if (square.coord.y == 1) {
                Square move = game.get(new Coord(square.coord.x, 3));
                Square tmp = game.get(new Coord(square.coord.x, 2));
                if (move!=null&&move.get()==null&&tmp!=null&&tmp.get()==null)
                    possibleMoves.add(move);
            }

            Square move = game.get(new Coord(square.coord.x, square.coord.y+1));
            boolean moveIsClear = false;

            if (move != null)
                if (move.get() == null) {
                    moveIsClear = true;
                }
            if (moveIsClear)
                possibleMoves.add(move);
            move = game.get(new Coord(square.coord.x-1, square.coord.y+1));

            if (move != null)
                if (move.get() != null)
                    if (move.get().color != color){
                        possibleMoves.add(move);
                    }
            move = game.get(new Coord(square.coord.x+1, square.coord.y+1));
            if (move != null) {
                if (move.get() != null) {
                    if (move.get().color != color) {
                        possibleMoves.add(move);
                    }
                }
            }
        }
        return possibleMoves;
    }
}
