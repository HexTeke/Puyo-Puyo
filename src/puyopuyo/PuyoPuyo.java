package puyopuyo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuyoPuyo extends JFrame {
    
    public static Process proc;
    
    public static void main(String[] args) {
        PuyoPuyo game = new PuyoPuyo();
    }
    
    protected KeyBinding keyBinds;
    protected Menu menu = null;
    
    public PuyoPuyo() {
        super("Puyo Puyo!");
        System.out.println("Initializing Puyo Puyo...");
        initFrame();
        
        keyBinds = new KeyBinding();
    }
    
    private void initFrame() {
        setIcon();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addComponents();
        pack();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addComponents() {
        menu = new Menu(this, keyBinds);
        add(menu);
    }
    
    private void setIcon() {
        try {
            BufferedImage buff = ImageIO.read(
                    this.getClass().getResource("/puyopuyo/img/icon.png"));
            setIconImage(new ImageIcon(buff).getImage());
        }
        catch(IOException| IllegalArgumentException ex) {
            System.err.println("Error: File not found /puyopuyo/img/icon.png");
        }
    }
    
    public int getFrameWidth() {
        return getContentPane().getWidth();
    }
    
    public int getFrameHeight() {
        return getContentPane().getHeight();
    }
    
}
