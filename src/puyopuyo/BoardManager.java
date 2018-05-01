package puyopuyo;

import java.awt.Image;
import java.util.Random;

/**
 *
 * @author HexTeke
 */
public class BoardManager {
    
    private final PuyoPuyo frame;
    private final BoardLeft board;
    
    private final String side;
    private Puyo[][] puyoGrid;
    
    public BoardManager(PuyoPuyo frame, BoardLeft board, String side) {
        this.frame = frame;
        this.board = board;
        this.side = side;
        
        populateGrid();
        getConnections();
    }
    
    private void populateGrid() {
        puyoGrid = new Puyo[12][6];
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                String col = new String();
                int rand = new Random().nextInt(5);
                switch(rand) {
                    case 0:
                        col = "red";
                        break;
                    case 1:
                        col = "green";
                        break;
                    case 2:
                        col = "yellow";
                        break;
                    case 3:
                        col = "violet";
                        break;
                    case 4:
                        col = "blue";
                        break;
                }
                puyoGrid[i][j] = new Puyo(
                    frame,
                    (16 * frame.scale) + (j * 16 * frame.scale),
                    (16 * frame.scale) + (i * 16 * frame.scale),
                    col,
                    true
                );
            }
        }
    }
    
    // logic for connecting puyos
    private void getConnections() {
        String color;
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(puyoGrid[i][j].isVisible()) {
                    color = puyoGrid[i][j].getColor();
                    if(j > 0) { // LEFT
                        if(puyoGrid[i][j - 1].isVisible() && color.equals(puyoGrid[i][j - 1].getColor()))
                            puyoGrid[i][j].update(4, true);
                        else
                            puyoGrid[i][j].update(4, false);
                    }
                    if(j < 5) { // RIGHT
                        if(puyoGrid[i][j + 1].isVisible() && color.equals(puyoGrid[i][j + 1].getColor()))
                            puyoGrid[i][j].update(3, true);
                        else
                            puyoGrid[i][j].update(3, false);
                    }
                    if(i > 0) { // UP
                        if(puyoGrid[i - 1][j].isVisible() && color.equals(puyoGrid[i - 1][j].getColor()))
                            puyoGrid[i][j].update(1, true);
                        else
                            puyoGrid[i][j].update(1, false);
                    }
                    if(i < 11) { // DOWN
                        if(puyoGrid[i + 1][j].isVisible() && color.equals(puyoGrid[i + 1][j].getColor()))
                            puyoGrid[i][j].update(2, true);
                        else
                            puyoGrid[i][j].update(2, false);
                    }
                }
            }
        }
    }
    
    public int getX(int x, int y) {
        return puyoGrid[x][y].getX();
    }
    
    public int getY(int x, int y) {
        return puyoGrid[x][y].getY();
    }
    
    public boolean getVisibility(int x, int y) {
        return puyoGrid[x][y].isVisible();
    }
    
    public Image getImage(int x, int y) {
        return puyoGrid[x][y].getImage();
    }
    
}
