package chess;

import chess.Piece.*;

public class Board {
    private Square[][] squares;
    public static Square whiteKing;
    public static Square blackKing;
    public Board() {
        squares = new Square[8][8];
    }

    public void start() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(new Coord(i, j), null);
            }
        }
        for (int i = 0; i < 8; i++) {
            squares[i][1] = new Square(new Coord(i, 1), new Pawn(PieceColor.BLACK));
            squares[i][6] = new Square(new Coord(i, 6), new Pawn(PieceColor.WHITE));
        }
        whiteKing = new Square(new Coord(4, 7), new King(PieceColor.WHITE));
        squares[4][7] = whiteKing;
        blackKing =  new Square(new Coord(4, 0), new King(PieceColor.BLACK));
        squares[4][0] = blackKing;

        squares[2][0] = new Square(new Coord(2, 0), new Bishop(PieceColor.BLACK));
        squares[2][7] = new Square(new Coord(2, 7), new Bishop(PieceColor.WHITE));
        squares[5][0] = new Square(new Coord(5, 0), new Bishop(PieceColor.BLACK));
        squares[5][7] = new Square(new Coord(5, 7), new Bishop(PieceColor.WHITE));

        squares[0][0] = new Square(new Coord(0,0), new Rook(PieceColor.BLACK));
        squares[0][7] = new Square(new Coord(0,7), new Rook(PieceColor.WHITE));
        squares[7][0] = new Square(new Coord(7,0), new Rook(PieceColor.BLACK));
        squares[7][7] = new Square(new Coord(7,7), new Rook(PieceColor.WHITE));

        squares[1][0] = new Square(new Coord(1, 0), new Knight(PieceColor.BLACK));
        squares[6][0] = new Square(new Coord(6, 0), new Knight(PieceColor.BLACK));
        squares[1][7] = new Square(new Coord(1, 7), new Knight(PieceColor.WHITE));
        squares[6][7] = new Square(new Coord(6, 7), new Knight(PieceColor.WHITE));

        squares[3][0] = new Square(new Coord(3, 0), new Queen(PieceColor.BLACK));
        squares[3][7] = new Square(new Coord(3, 7), new Queen(PieceColor.WHITE));
    }

    public Square[][] getSquares() {
        return squares;
    }


    public Square get(Coord coord) {
        if (Ranges.inRange(coord))
            return squares[coord.x][coord.y];
        return null;
    }

}
