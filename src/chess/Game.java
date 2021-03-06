package chess;

import chess.Piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Board board;
    private PieceColor whoWalks;
    public Square selectedFigure;
    public KingStatus ks;
    public List<Square> possibleMoves;
    public GameStatus status;
    public boolean[] castles;
    public Coord getCoordWhiteKing() {
        return board.whiteKing.coord;
    }
    public Coord getCoordBlackKing() {
        return board.blackKing.coord;
    }

    public Game()
    {
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void start() {
         status = GameStatus.START;
         ks = KingStatus.NOTHING;
         selectedFigure=null;
         possibleMoves=new ArrayList<>();
         whoWalks = PieceColor.WHITE;
         castles = new boolean[]{true, true, true, true};
         board.start();
    }

    private int getCountOfPossibleMoves(PieceColor color) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square tmp = board.getSquares()[i][j];
                if (tmp.get()!=null&&tmp.get().color == color){
                    List<Square> pms = tmp.get().getPossibleMoves(this);
                    int x = filterMoves(pms, tmp).size();
                    count+=x;
                }
            }
        }
        return count;
    }

    public Square get(Coord coord) {
        return board.get(coord);
    }

    public void pressLeft(Coord coord) {
        Square square = get(coord);
        System.out.println(Arrays.toString(castles));
        if (selectedFigure == null)
            ifSelectedFigureEqualNull(square);
        else
            ifSelectedFigureNotEqualNull(square);
        checkOnWinner();
        check();
    }

    private void checkOnWinner() {
        if (status != GameStatus.START)
            start();
    }

    private void check() {
        if (checkBanForWhite())
            ks=KingStatus.WHITE_CHECK;
        else if (checkBanForBlack())
            ks=KingStatus.BLACK_CHECK;
        else
            ks=KingStatus.NOTHING;
        if (getCountOfPossibleMoves(whoWalks) == 0)
        {
            KingStatus ks1;
            if (whoWalks==PieceColor.WHITE)
                ks1=KingStatus.WHITE_CHECK;
            else
                ks1=KingStatus.BLACK_CHECK;
            if (ks==ks1)
            {
                status = GameStatus.CHECKMATE;
                status.setColor(whoWalks==PieceColor.WHITE? PieceColor.BLACK : PieceColor.WHITE);
            }
            else
                status = GameStatus.PAT;
        }
    }

    public List<Square> filterMoves(List<Square> possibleMoves, Square ... s) {
        Square t;
        if (s.length>0)
            t = s[0];
        else
            t=selectedFigure;
        for (int i = possibleMoves.size()-1; i>=0; i--)
        {
            Square pm = possibleMoves.get(i);
            if (t != null)
            {
                Piece p = pm.get();
                move(pm, t);
                if (pm.get().color == PieceColor.WHITE)
                {
                    if (checkBanForWhite()){
                        possibleMoves.remove(i);
                    }
                } else {
                    if (checkBanForBlack()){
                        possibleMoves.remove(i);
                    }
                }
                move(t, pm);
                pm.set(p);
            }
        }
        return possibleMoves;
    }

    public boolean checkBanForBlack() {
        Square king=Board.blackKing;
        int x = king.coord.x, y = king.coord.y;
        boolean found = false;


        List<Coord> coords = Ranges.getCoordsAroundSquare(king.coord);

        for (Coord arnd : coords) {
            Square tmp = get(arnd);
            if (tmp!=null&&tmp.get()!=null&&tmp.get().type==PieceType.WHITE_KING)
            {
                return true;
            }
        }

        int[][] pawns = {
                {1, 1},
                {-1, 1}
        };

        for (int[] o : pawns) {
            Square tmp = get(new Coord(x + o[0], y + o[1]));
            if (tmp != null && tmp.get() != null) {
                if (tmp.get().type == PieceType.WHITE_PAWN) {
                    return true;
                }
            }
        }

        int[][] knights = {
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1}
        };

        for (int[] o : knights)
        {
            Square tmp = get(new Coord(x+o[0], y+o[1]));
            if (tmp!=null&&tmp.get()!=null)
                if (tmp.get().type == PieceType.WHITE_KNIGHT) {
                    return true;
                }
        }

        int[][] directions = {
                {1, 1, 0},
                {1, -1, 0},
                {-1, -1, 0},
                {-1, 1, 0},
                {1, 0, 0},
                {-1, 0, 0},
                {0, 1, 0},
                {0, -1, 0}
        };
        for (int i = 1; i<8; i++) {
            for (int[] o: directions) {
                if (o[2] == 1) {
                    continue;
                }
                Square tmp = get(new Coord(x+o[0]*i, y+o[1]*i));
                if (tmp == null)
                {
                    o[2] = 1;
                    continue;
                }
                if (tmp.get() == null)
                {
                    continue;
                }
                boolean j = o[0]==0;
                boolean k = o[1]==0;
                PieceType t;
                if (!j && !k)
                    t=PieceType.WHITE_BISHOP;
                else
                    t=PieceType.WHITE_ROOK;
                if (tmp.get().type == t || tmp.get().type == PieceType.WHITE_QUEEN)
                {
                    return true;
                } else
                    o[2] = 1;
            }
        }

        return false;
    }

    public boolean checkBanForWhite() {
        Square king = Board.whiteKing;
        int x = king.coord.x, y = king.coord.y;

        List<Coord> coords = Ranges.getCoordsAroundSquare(king.coord);

        for (Coord arnd : coords) {
            Square tmp = get(arnd);
            if (tmp!=null&&tmp.get()!=null&&tmp.get().type==PieceType.BLACK_KING)
            {
                return true;
            }
        }

        int[][] pawns = {
                {1, -1},
                {-1, -1}
        };

        for (int[] o : pawns) {
            Square tmp = get(new Coord(x + o[0], y + o[1]));
            if (tmp != null && tmp.get() != null) {
                if (tmp.get().type == PieceType.BLACK_PAWN) {
                    return true;
                }
            }
        }

        int[][] knights = {
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1}
        };

        for (int[] o : knights)
        {
            Square tmp = get(new Coord(x+o[0], y+o[1]));
            if (tmp!=null&&tmp.get()!=null)
                if (tmp.get().type == PieceType.BLACK_KNIGHT) {
                    return true;
                }
        }

        int[][] directions = {
                {1, 1, 0},
                {1, -1, 0},
                {-1, -1, 0},
                {-1, 1, 0},
                {1, 0, 0},
                {-1, 0, 0},
                {0, 1, 0},
                {0, -1, 0}
        };
        Label : for (int i = 1; i<8; i++) {
            for (int[] o: directions) {
                if (o[2] == 1) {
                    continue;
                }
                Square tmp = get(new Coord(x+o[0]*i, y+o[1]*i));
                if (tmp == null)
                {
                    o[2] = 1;
                    continue;
                }
                if (tmp.get() == null)
                {
                    continue;
                }
                boolean j = o[0]==0;
                boolean k = o[1]==0;
                PieceType t;
                if (!j && !k)
                    t=PieceType.BLACK_BISHOP;
                else
                    t=PieceType.BLACK_ROOK;
                if (tmp.get().type == t || tmp.get().type == PieceType.BLACK_QUEEN)
                {
                    return true;
                } else
                    o[2] = 1;
            }
        }

        return false;
    }

    public void ifSelectedFigureEqualNull(Square square) {
        if (square.get() != null)
        {
            if (square.get().color == whoWalks)
            {
                selectedFigure = square;
                possibleMoves = square.get().getPossibleMoves(this);
                possibleMoves = filterMoves(possibleMoves);
            }
        }
    }

    private void moveRook(int num) {
        Square mov;
        Square tmp;
        switch (num) {
            case 0:
                tmp = get(new Coord(0, 7));
                mov = get(new Coord(3, 7));
                move(mov, tmp);
                break;
            case 1:
                tmp = get(new Coord(7, 7));
                mov = get(new Coord(5, 7));
                move(mov, tmp);
                break;
            case 2:
                tmp = get(new Coord(0, 0));
                mov = get(new Coord(3, 0));
                move(mov, tmp);
                break;
            case 3:
                tmp = get(new Coord(7, 0));
                mov = get(new Coord(5, 0));
                move(mov, tmp);
                break;
        }
    }

    public void move(Square pm, Square selectedFigure) {
        pm.set(selectedFigure.get());
        if (selectedFigure.get() instanceof King)
        {
            if (selectedFigure.get().color == PieceColor.WHITE)
            {
                Board.whiteKing = pm;
            } else {
                Board.blackKing = pm;
            }
        }
        selectedFigure.remove();
    }
    public void ifSelectedFigureNotEqualNull(Square square){

        boolean isPossible = false;
        for (Square pm : possibleMoves) {
            if (pm.equals(square))
            {
                isPossible = true;
                if (selectedFigure.get() instanceof Rook)
                {
                    Coord t = selectedFigure.coord;
                    if (t.equals(new Coord(0, 7)))
                        castles[0]=false;
                    else if (t.equals(new Coord(7, 7)))
                        castles[1]=false;
                    else if (t.equals(new Coord(0,0)))
                        castles[2]=false;
                    else if (t.equals((new Coord(7, 0))))
                        castles[3]=false;
                }
                if (selectedFigure.get() instanceof King) {

                    if (selectedFigure.get().color == PieceColor.WHITE){
                        if (Board.whiteKing.coord.equals(new Coord(4,7))) {
                            if (pm.coord.equals(new Coord(2, 7)))
                                moveRook(0);
                            if (pm.coord.equals(new Coord(6, 7)))
                                moveRook(1);
                        }
                        Board.whiteKing = pm;
                        castles[0] = false;
                        castles[1] = false;
                    }
                    else {
                        if (Board.blackKing.coord.equals(new Coord(4,0)))
                        {
                            if (pm.coord.equals(new Coord(2,0)))
                                moveRook(2);
                            if (pm.coord.equals(new Coord(6,0)))
                                moveRook(3);
                        }
                        Board.blackKing = pm;
                        castles[2] = false;
                        castles[3] = false;
                    }
                }
                if ((pm.coord.y==0 || pm.coord.y==7) && pm.get() instanceof Pawn)
                {
                    Piece fig = new Queen(pm.get().color);
                    pm.set(fig);
                }
                move(pm, selectedFigure);
                this.selectedFigure = null;
                possibleMoves = new ArrayList<>();
                whoWalks = whoWalks == PieceColor.WHITE? PieceColor.BLACK : PieceColor.WHITE;
            }
        }
        if (!isPossible) {
            selectedFigure = null;
            possibleMoves = new ArrayList<>();
        }
    }
    public enum KingStatus {
        WHITE_CHECK,
        BLACK_CHECK,
        NOTHING
    }
}
