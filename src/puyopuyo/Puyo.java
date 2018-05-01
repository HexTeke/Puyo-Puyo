package puyopuyo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/* Key:
 * 00[0] - default
 * 01 - selected
 * 02 - pop
 * 10[1] - up
 * 11[2] - down
 * 12[3] - right
 * 13[4] - left
 * 20 - left/right
 * 21 - up/down
 * 30 - up/right
 * 31 - right/down
 * 32 - left/up
 * 33 - down/left
 * 40 - up/right/down
 * 41 - left/down/right
 * 42 - up/left/down
 * 43 - left/up/right
 * 50 - all
 * 
 */

public class Puyo {
    
    private PuyoPuyo frame;
    
    private String imgPath;
    
    private int xPos;
    private int yPos;
    private String color; // GREY, RED, BLUE, GREEN, YELLOW, VIOLET
    
    private boolean visible;
    private int current;
    private boolean[] connections;
    
    public Puyo(PuyoPuyo frame, int x, int y, String c, Boolean vis) {
        this.frame = frame;
        xPos = x;
        yPos = y;
        color = c;
        visible = vis;
        current = 01;
        connections = new boolean[5];
        
        imgPath = "/puyopuyo/img/" + color + "/";
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
    
    public boolean isVisible() {
        return visible;
    }
    
    public void update(int o, boolean val) {
        connections[o] = val;

        if(connections[1] && connections[2] && connections[3] && connections[4])
            current = 50;
        
        // 3-WAY
        else if(connections[1] && connections[2] && connections[3])
            current = 40;
        else if(connections[2] && connections[3] && connections[4])
            current = 41;
        else if(connections[1] && connections[2] && connections[4])
            current = 42;
        else if(connections[1] && connections[3] && connections[4])
            current = 43;
        
        // 2-WAY
        else if(connections[1] && connections[3])
            current = 30;
        else if(connections[2] && connections[3])
            current = 31;
        else if(connections[1] && connections[4])
            current = 32;
        else if(connections[2] && connections[4])
            current = 33;
        else if(connections[3] && connections[4])
            current = 20;
        else if(connections[1] && connections[2])
            current = 21;
        
        // 1-WAY
        else if(connections[1])
            current = 10;
        else if(connections[2])
            current = 11;
        else if(connections[3])
            current = 12;
        else if(connections[4])
            current = 13;
        
        // NO CONNECTIONS
        else
            current = 60;
    }
    
    public Image getImage() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource(imgPath + current + ".png"));
            Image scaledImg = img.getScaledInstance(16 * frame.scale, 16 * frame.scale, Image.SCALE_FAST);
            return scaledImg;
        }
        catch(IOException | IllegalArgumentException ex) {
            System.err.println("Error: file not found " + imgPath + current + ".png");
        }
        return null;
    }
    
}
