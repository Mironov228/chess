import chess.*;
import chess.Piece.Piece;
import chess.Piece.PieceColor;
import chess.Piece.PieceType;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chess extends JFrame
{
    private Game game;
    private final int SQUARE_SIZE = 80;
    private JLabel label;
    public Chess()
    {
        game = new Game();
        Ranges.start();
        game.start();
        initImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void setLabel() {
        switch (game.status) {
            case START:
                label.setText("Think twice");
                break;
            case CHECKMATE:
                label.setText(game.status.getColor().toString().toLowerCase() + " is winner");
                break;
            case PAT:
                label.setText("pat!!!");
        }
    }

    private void initLabel(){
        label = new JLabel();
        label.setText("Think twice");
        label.setFont(new Font("Arial", Font.ITALIC, 40));
        add(label, BorderLayout.SOUTH);
    }

    private void initFrame()
    {
        setTitle("Chess make by Gleb");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initPanel() {
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                for (Coord coord : Ranges.getAllCoords()) {
                    Graphics2D g2 = (Graphics2D) g;
                    int x = coord.x+1;
                    int y = coord.y+1;
                    g2.setColor(Color.DARK_GRAY);
                    if ((x%2==0&&y%2==0)||(x%2==1&&y%2==1)) {
                        g2.setColor(Color.LIGHT_GRAY);
                    }
                    g2.fillRect(coord.x*SQUARE_SIZE, coord.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    Square square = game.get(coord);

                    if (square != null && square.get() != null)
                    {
                        g2.drawImage((Image) square.get().type.image, square.coord.x*SQUARE_SIZE, square.coord.y*SQUARE_SIZE, this);
                    }
                    if (square.equals(game.selectedFigure))
                    {
                        drawRect(g2, coord, Color.GREEN);
                    }
                    for (Square sqr: game.possibleMoves)
                    {
                        drawRect(g2, sqr.coord, Color.ORANGE);
                    }
                    switch(game.ks)
                    {
                        case WHITE_CHECK:
                            drawRect(g2, game.getCoordWhiteKing(), Color.RED);
                            break;
                        case BLACK_CHECK:
                            drawRect(g2, game.getCoordBlackKing(), Color.RED);
                            break;
                    }
                }
            }
        };
        panel.setPreferredSize(new Dimension(8*SQUARE_SIZE, 8*SQUARE_SIZE));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()/SQUARE_SIZE;
                int y = e.getY()/SQUARE_SIZE;

                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeft(new Coord(x,y));
                    repaint();
                    setLabel();
                }
            }
        });


        add(panel);
    }

    private void drawRect(Graphics2D g2, Coord coord, Color color) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(coord.x*SQUARE_SIZE, coord.y*SQUARE_SIZE, SQUARE_SIZE-3, SQUARE_SIZE-3);
    }

    private void initImages() {
        for (PieceType pt : PieceType.values()) {
            String color = pt.toString().substring(0, 5).toLowerCase();
            String figure = pt.toString().substring(6).toLowerCase();
            String filepath = "/img/chessfigures/"+color+"/"+figure+".png";
            pt.image = getImage(filepath);
        }
    }

    private Image getImage(String name) {
        ImageIcon ii = new ImageIcon(getClass().getResource(name));
        Image img = ii.getImage();
        return img;
    }

    public static void main(String[] args)
    {
        new Chess();
    }
}
