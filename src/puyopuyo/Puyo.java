package puyopuyo;

public class Puyo {
    
    private int xPos;
    private int yPos;
    private String color; // GREY, RED, TEAL, GREEN, YELLOW, VIOLET
    
    public Puyo(int x, int y, String c) {
        xPos = x;
        yPos = y;
        color = c;
    }
    
    /* Movement */
    public void setX(int x) {
        xPos = x;
    }
    
    public void setY(int y) {
        yPos = y;
    }
    
    public void setPos(int x, int y) {
        xPos = x; yPos = y;
    }
    
    public void moveX(int val) {
        xPos += val;
    }
    
    public void moveY(int val) {
        yPos += val;
    }
    
    public int getX() {
        return xPos;
    }
    
    public int getY() {
        return yPos;
    }
    
    /* Color */
    public void setColor(String c) {
        color = c;
    }
    
    public String getColor() {
        return color;
    }
    
}
