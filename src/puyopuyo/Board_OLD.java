package puyopuyo;

public class Board_OLD {
    private int[][] grid;
    private int width, height;
    
    public Board_OLD(int w, int h) { // default: 6x14
        grid = new int[width][height];
        width = w;
        height = h;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
}
