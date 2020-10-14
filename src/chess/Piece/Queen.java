package chess.Piece;

import chess.Coord;
import chess.Game;
import chess.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public List<Square> getPossibleMoves(Game game) {
        List<Square> possibleMoves = new ArrayList<>();

        boolean stop1 = false;
        boolean stop2 = false;
        boolean stop3 = false;
        boolean stop4 = false;
        boolean stop5 = false;
        boolean stop6 = false;
        boolean stop7 = false;
        boolean stop8 = false;


        for (int i = 1; !stop1 || !stop2 || !stop3 || !stop4 || !stop5 || !stop6 || !stop7 || !stop8; i++) {
            if (!stop5) {
                Square tmp = game.get(new Coord(square.coord.x+i, square.coord.y-i));
                if (tmp != null) {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color) {
                        possibleMoves.add(tmp);
                        stop5 = true;
                    } else if (tmp.get().color == color) {
                        stop5 = true;
                    }
                } else {
                    stop5 = true;
                }
            }
            if (!stop6) {
                Square tmp = game.get(new Coord(square.coord.x-i, square.coord.y-i));
                if (tmp != null) {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color) {
                        possibleMoves.add(tmp);
                        stop6 = true;
                    } else if (tmp.get().color == color) {
                        stop6 = true;
                    }
                } else {
                    stop6 = true;
                }
            }
            if (!stop7) {
                Square tmp = game.get(new Coord(square.coord.x-i, square.coord.y+i));
                if (tmp != null) {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color) {
                        possibleMoves.add(tmp);
                        stop7 = true;
                    } else if (tmp.get().color == color) {
                        stop7 = true;
                    }
                } else {
                    stop7 = true;
                }
            }
            if (!stop8) {
                Square tmp = game.get(new Coord(square.coord.x+i, square.coord.y+i));
                if (tmp != null) {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color) {
                        possibleMoves.add(tmp);
                        stop8 = true;
                    } else if (tmp.get().color == color) {
                        stop8 = true;
                    }
                } else {
                    stop8 = true;
                }
            }
            if (!stop1) {
                Square tmp = game.get(new Coord(square.coord.x+i, square.coord.y));
                if (tmp != null)
                {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color)
                    {
                        possibleMoves.add(tmp);
                        stop1 = true;
                    } else if (tmp.get().color == color)
                        stop1=true;
                }
                else
                    stop1 = true;
            }
            if (!stop2) {
                Square tmp = game.get(new Coord(square.coord.x-i, square.coord.y));
                if (tmp != null) {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color) {
                        possibleMoves.add(tmp);
                        stop2 = true;
                    } else if (tmp.get().color == color)
                        stop2 = true;
                }
                else
                    stop2 = true;
            }
            if (!stop3) {
                Square tmp = game.get(new Coord(square.coord.x, square.coord.y+i));
                if (tmp != null) {
                    if (tmp.get() == null)
                    {
                        possibleMoves.add(tmp);
                    }
                    else if (tmp.get().color != color)
                    {
                        possibleMoves.add(tmp);
                        stop3 = true;
                    } else if (tmp.get().color == color)
                    {
                        stop3=true;
                    }
                }
                else
                    stop3 = true;
            }
            if (!stop4) {
                Square tmp = game.get(new Coord(square.coord.x, square.coord.y-i));
                if (tmp != null) {
                    if (tmp.get() == null)
                        possibleMoves.add(tmp);
                    else if (tmp.get().color != color) {
                        possibleMoves.add(tmp);
                        stop4 = true;
                    } else if (tmp.get().color == color)
                        stop4 = true;
                }
                else
                    stop4 = true;
            }
        }

        return possibleMoves;
    }
}
