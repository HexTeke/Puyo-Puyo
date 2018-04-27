package puyopuyo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuyoPuyo extends JFrame {
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            PuyoPuyo game = new PuyoPuyo();
        });
    }
    
    protected Menu menu;
    private int width, height;
    
    public PuyoPuyo() {
        super("Puyo Puyo!");
        width = 320 * 2;
        height = 224 * 2;
        initFrame();
    }
    
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIcon();
        
        SoundManager.playSong(0);
        menu = new Menu(this);
        add(menu);
        
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
    
    public void setDefinedWidth(int val) {
        width = val;
    }
    
    public void setDefinedHeight(int val) {
        height = val;
    }
    
    public int getDefinedWidth() {
        return width;
    }

    public int getDefinedHeight() {
        return height;
    }
}
