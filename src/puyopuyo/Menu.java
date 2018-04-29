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

public abstract class Menu extends JPanel {
    protected PuyoPuyo frame;
    protected PanelContainer pc;
    protected String imgPath;
    protected int selected;
    
    // initialize future keyBindings for child Menus
    protected static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    protected static final String MOVE_UP = "move up";
    protected static final String MOVE_DOWN = "move down";
    protected static final String MOVE_LEFT = "move left";
    protected static final String MOVE_RIGHT = "move right";
    protected static final String SELECT = "select";
    protected static final String BACK = "back";
    protected static final String PAUSE = "pause";
    
    public Menu(PuyoPuyo frame, PanelContainer pc) {
        this.frame = frame;
        this.pc = pc;
    }

    // returns the scaled image for paintComponent to use
    private Image getImage() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource(imgPath + selected + ".png"));
            Image scaledImg = img.getScaledInstance(frame.width, frame.height, Image.SCALE_FAST);
            return scaledImg;
        }
        catch(IOException | IllegalArgumentException ex) {
            System.err.println("Error: file not found /puyopuyo/img/menu" + selected + ".png");
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, this);
    }
    
    abstract public void navigate(int action);
}

class MainMenu extends Menu {
    
    public MainMenu(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        imgPath = "/puyopuyo/img/menu0";
        selected = 0;

        getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);      // adds the inputs to the
        getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);  // component. 
        getInputMap(IFW).put(KeyStroke.getKeyStroke("ENTER"), SELECT);
        getActionMap().put(MOVE_UP, new Action(this, 0));       // calls the child class Action
        getActionMap().put(MOVE_DOWN, new Action(this, 1));     // to respond whenever an input
        getActionMap().put(SELECT, new Action(this, 2));        // has been recieved.
    }
    
    @Override
    public void navigate(int action) {  // handles menu navigation logic...
        switch(action) {
            case 0: // UP
                if(selected > 0) {
                    frame.playSFX(10);
                    selected--;
                }   break;
            case 1: // DOWN
                if(selected < 3) {
                    frame.playSFX(10);
                    selected++;
                }   break;
            case 2: // ENTER
                if(selected == 0) { // SCENARIO
                    frame.playSFX(11);
                }
                if(selected == 1) { // 1P VS. 2P MODE
                    frame.playSFX(11);
                }
                if(selected == 2) { // EXERCISE MODE
                    selected = 0; // "resets" MainMenu back to it's original state
                    frame.playSFX(17);
                    pc.swapCard("game");
                    frame.playSong(2);
                }
                if(selected == 3) { // OPTIONS
                    frame.playSFX(17);
                    pc.swapCard("options");
                }   break;
        }
    }
    
    private class Action extends AbstractAction {
        private final MainMenu main;
        private final int action;
        
        Action(MainMenu main, int action) {
            this.main = main;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            main.navigate(action);
        }
    }
}

class OptionsMenu extends Menu {
    
    public OptionsMenu(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        imgPath = "/puyopuyo/img/menu1";
        selected = 0;
        
        getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("ENTER"), SELECT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), BACK);
        
        getActionMap().put(MOVE_LEFT, new Action(this, 0));
        getActionMap().put(MOVE_RIGHT, new Action(this, 1));
        getActionMap().put(SELECT, new Action(this, 2));
        getActionMap().put(BACK, new Action(this, 3));
    }
    
    @Override
    public void navigate(int action) {
        switch(action) {
            case 0: // LEFT
                break;
            case 1: // RIGHT
                break;
            case 2: // ENTER
                break;
            case 3: // ESCAPE
                pc.swapCard("main");
                frame.playSFX(15);
                break;
        }
    }
    
    private class Action extends AbstractAction {
        private final OptionsMenu options;
        private final int action;
        
        Action(OptionsMenu main, int action) {
            this.options = main;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            options.navigate(action);
        }
    }
}

class PauseMenu extends Menu {
    
    public PauseMenu(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        imgPath = "/puyopuyo/img/menu2";
        selected = 0;
        
        getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("ENTER"), SELECT);
        
        getActionMap().put(MOVE_UP, new Action(this, 0));
        getActionMap().put(MOVE_DOWN, new Action(this, 1));
        getActionMap().put(SELECT, new Action(this, 2));
    }
    
    @Override
    public void navigate(int action) {
        switch(action) {
            case 0: // UP
                if(selected > 0) {
                    selected--;
                    frame.playSFX(10);
                }   break;
            case 1: // DOWN
                if(selected < 1) {
                    selected++;
                    frame.playSFX(10);
                }   break;
            case 2: // ENTER
                if(selected == 0) { // CONTINUE
                    selected = 0;
                    frame.playSFX(17);
                    pc.swapCard("game");
                }
                if(selected == 1) { // QUIT
                    selected = 0;
                    frame.playSFX(11);
                    frame.playSong(0);
                    pc.swapCard("main");
                }   break;
        }
    }
    
    private class Action extends AbstractAction {
        private final PauseMenu pause;
        private final int action;
        
        Action(PauseMenu pause, int action) {
            this.pause = pause;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pause.navigate(action);
        }
    }
}