package puyopuyo;

public class Board {
    private int[][] grid;
    private int width, height;
    
    public Board(int w, int h) { // default: 6x14
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
