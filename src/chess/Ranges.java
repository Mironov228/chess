package chess;

import java.util.ArrayList;
import java.util.List;

public class Ranges {
    private static List<Coord> allCoords;

    public static void start() {
        allCoords = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                allCoords.add(new Coord(i, j));
            }
        }
    }

    public static List<Coord> getAllCoords() {
        return allCoords;
    }

    public static boolean inRange(Coord coord) {
        return coord.x>=0 && coord.x<8 &&
                coord.y>=0 && coord.y<8;
    }

    public static List<Coord> getCoordsAroundSquare(Coord coord) {
        List<Coord> coordsAround = new ArrayList<>();
        for (int x = coord.x-1; x <= coord.x+1; x++) {
            for (int y = coord.y-1; y <= coord.y+1; y++) {
                if (!coord.equals(new Coord(x, y)) && inRange(new Coord(x, y)))
                    coordsAround.add(new Coord(x, y));
            }
        }
        return coordsAround;
    }

}
