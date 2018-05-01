package puyopuyo;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

// see Menu class for more info, roughly follows the same format
public class Board extends JPanel {
    protected PuyoPuyo frame;
    protected PanelContainer pc;
    protected String imgPath;
    protected int imgWidth, imgHeight;
    protected Image image;
    
    public Board(PuyoPuyo frame, PanelContainer pc) {
        this.frame = frame;
        this.pc = pc;
        
        imgHeight = 224 * frame.scale;
    }
    
    final protected void createImage() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource(imgPath + ".png"));
            image = img.getScaledInstance(imgWidth, imgHeight, Image.SCALE_FAST);
        }
        catch(IOException | IllegalArgumentException ex) {
            System.err.println("Error: file not found " + imgPath + ".png");
        }
    }
    
    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, this);
    }
    */
}

// a JPanel that contains all of the Board Panels
// pause functionality is implemented here
class BoardContainer extends JPanel {
    
    private final PuyoPuyo frame;
    private final PanelContainer pc;
    
    private final Board left;
    private final Board middle;
    private final Board right;
    
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String PAUSE = "pause";
    
    public BoardContainer(PuyoPuyo frame, PanelContainer pc) {
        this.frame = frame;
        this.pc = pc;
        left = new BoardLeft(frame, pc);
        middle = new BoardMiddle(frame, pc);
        right = new BoardRight(frame, pc);
        
        getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), PAUSE);
        getActionMap().put(PAUSE, new Action(this, 0));
        initialize();
    }
    
    private void initialize() {
        setPreferredSize(new Dimension(frame.width, frame.height));
        setLayout(new BorderLayout());
        
        left.setPreferredSize(new Dimension(112 * frame.scale, 224 * frame.scale));
        middle.setPreferredSize(new Dimension(96 * frame.scale, 224 * frame.scale));
        right.setPreferredSize(new Dimension(112 * frame.scale, 224 * frame.scale));
        
        add(left, BorderLayout.WEST);
        add(middle, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }
    
    private class Action extends AbstractAction {
        private final BoardContainer game;
        private final int action;
        
        Action(BoardContainer game, int action) {
            this.game = game;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(action == 0) {
                frame.playSFX(17);
                pc.swapCard("pause");
            }
        }
    }
}

class BoardLeft extends Board {
    
    private final BoardManager boardManager;
    
    public BoardLeft(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        imgPath = "/puyopuyo/img/board1";
        imgWidth = 112 * frame.scale;
        
        boardManager = new BoardManager(frame, this, "left");
        createImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(boardManager.getVisibility(i, j)) {
                    g.drawImage(
                        boardManager.getImage(i, j),
                        boardManager.getX(i, j),
                        boardManager.getY(i, j),
                        this
                    );
                }
            }
        }
    }
}

class BoardMiddle extends Board {
    
    public BoardMiddle(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        imgPath = "/puyopuyo/img/board2";
        imgWidth = 96 * frame.scale;
        
        createImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
}

class BoardRight extends Board {
    
    public BoardRight(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        imgPath = "/puyopuyo/img/board3";
        imgWidth = 112 * frame.scale;
        
        createImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
}
