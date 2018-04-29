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
    
    private final String iconPath = "/puyopuyo/img/icon.png";
    
    private final SoundManager soundManager;
    private final PanelContainer menu;
    public int width, height, scale;
    
    public PuyoPuyo() {
        super("Puyo Puyo!");
        scale = 2;
        width = 320 * scale;
        height = 224 * scale;
        
        soundManager = new SoundManager();
        menu = new PanelContainer(this);
        initFrame();
    }
    
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setIcon();
        
        playSong(0);
    }
    
    private void setIcon() {
        try {
            BufferedImage buf = ImageIO.read(this.getClass().getResource(iconPath));
            setIconImage(new ImageIcon(buf).getImage());
        }
        catch(IOException| IllegalArgumentException ex) {
            System.err.println("Error: File not found " + iconPath);
        }
    }
    
    public void playSFX(int o) {
        soundManager.playSFX(o);
    }
    
    public void playSong(int o) {
        soundManager.playSong(o);
    }
}
