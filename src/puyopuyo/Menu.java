package puyopuyo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Menu extends JPanel {

    protected PuyoPuyo frame;
    
    private String imgPath;
    private int selected;
    
    private boolean keyPressed;
    
    public Menu(PuyoPuyo p) {
        super();
        frame = p;
        selected = 1;
        imgPath = "/puyopuyo/img/fullmenu1.png";
        keyPressed = false;
        initMenu();
    }
    
    private void initMenu() {
        setPreferredSize(new Dimension(
                frame.getDefinedWidth(),
                frame.getDefinedHeight()
        ));
        setFocusable(true);
        requestFocusInWindow();
        
        /*
        addKeyListener(new KeyListener() {   
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(!keyPressed) {switch(keyCode) {
                    case KeyEvent.VK_DOWN:
                        navigate(KeyEvent.VK_DOWN);
                        keyPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        navigate(KeyEvent.VK_UP);
                        keyPressed = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        navigate(KeyEvent.VK_ENTER);
                        keyPressed = true;
                        break;
                    default:
                        break;
                }}
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPressed = false;
            }
        });
        */
        
        SoundManager.playSong(0);
    }
    
    /*
    private void navigate(int code) {
        switch (code) {
            case KeyEvent.VK_DOWN:
                if(selected != 4) {
                    selected++;
                    imgPath = "/puyopuyo/img/fullmenu" + selected + ".png";
                    SoundManager.playSFX(10);
                }   break;
            case KeyEvent.VK_UP:
                if(selected != 1) {
                    selected--;
                    imgPath = "/puyopuyo/img/fullmenu" + selected + ".png";
                    SoundManager.playSFX(10);
                }   break;
            case KeyEvent.VK_ENTER:
                if(selected == 1) {
                    SoundManager.playSFX(11);
                }
                if(selected == 2) {
                    SoundManager.playSFX(11);
                }
                if(selected == 3) {
                    SoundManager.playSFX(17);
                }
                if(selected == 4) {
                    SoundManager.playSFX(11);
                }   break;
            default:
                break;
        }
    }
    */
    
    private Image getImage() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource(imgPath));
            Image scaledImg = img.getScaledInstance(
                    frame.getContentPane().getWidth(),
                    frame.getContentPane().getHeight(),
                    Image.SCALE_FAST
            );
            return scaledImg;
        }
        catch(IOException | IllegalArgumentException ex) {
            System.err.println("Error: file not found /puyopuyo/img/fullmenu" + selected);
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, this);
    }
}