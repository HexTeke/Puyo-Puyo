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
    
    private SoundManager soundManager;
    private PanelContainer menu;
    public int width, height, scale;
    
    public PuyoPuyo() {
        super("Puyo Puyo!");
        scale = 1;
        width = 320 * scale;
        height = 224 * scale;
        soundManager = new SoundManager();
        initFrame();
    }
    
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIcon();
        
        playSong(0);
        menu = new PanelContainer(this);
        
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
    
    public void playSFX(int o) {
        soundManager.playSFX(o);
    }
    
    public void playSong(int o) {
        soundManager.playSong(o);
    }
}
