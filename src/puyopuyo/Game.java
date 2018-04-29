package puyopuyo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

// see Menu class for comments, follows the same format
public class Game extends JPanel {
    protected PuyoPuyo frame;
    protected PanelContainer pc;
    protected final String imgPath = "/puyopuyo/img/board";
    
    protected static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    protected static final String PAUSE = "pause";
    
    protected boolean pause;
    
    public Game(PuyoPuyo frame, PanelContainer pc) {
        this.frame = frame;
        this.pc = pc;

        getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), PAUSE);
        getActionMap().put(PAUSE, new Action(this, 0));
    }
    
    private class Action extends AbstractAction {
        private final Game game;
        private final int action;
        
        Action(Game game, int action) {
            this.game = game;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(action == 0) {
                game.pause = true;
                frame.playSFX(17);
                pc.swapCard("pause");
            }
        }
    }
    
    private Image getImage() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource(imgPath + ".png"));
            Image scaledImg = img.getScaledInstance(frame.width, frame.height, Image.SCALE_FAST);
            return scaledImg;
        }
        catch(IOException | IllegalArgumentException ex) {
            System.err.println("Error: file not found /puyopuyo/img/board" + ".png");
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, this);
    }
}


