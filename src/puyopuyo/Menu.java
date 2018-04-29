package puyopuyo;

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

public class Menu extends JPanel {
    protected PuyoPuyo frame;
    protected PanelContainer pc;
    protected String imgPath;
    protected int selected;
    protected boolean keyPressed;
    
    protected static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    protected static final String MOVE_UP = "move up";
    protected static final String MOVE_DOWN = "move down";
    protected static final String MOVE_LEFT = "move left";
    protected static final String MOVE_RIGHT = "move right";
    protected static final String PRESSEDSELECT = "pressed select";
    protected static final String RELEASEDSELECT = "released select";
    protected static final String BACK = "back";
    protected static final String PAUSE = "pause";
    
    public Menu(PuyoPuyo frame, PanelContainer pc) {
        this.frame = frame;
        this.pc = pc;
        imgPath = "/puyopuyo/img/menu";
        keyPressed = false;
        initialize();
    }
    
    private void initialize() {
        setPreferredSize(new Dimension(frame.width, frame.height));
    }
    
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
}

class MainMenu extends Menu {
    
    public MainMenu(PuyoPuyo frame, PanelContainer pc) {
        super(frame, pc);
        selected = 0;

        getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed ENTER"), PRESSEDSELECT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("released ENTER"), RELEASEDSELECT);
        
        getActionMap().put(MOVE_UP, new Action(this, 0));
        getActionMap().put(MOVE_DOWN, new Action(this, 1));
        getActionMap().put(PRESSEDSELECT, new Action(this, 2));
        getActionMap().put(RELEASEDSELECT, new Action(this, 3));
    }
    
    public void navigate(int action) {
        switch(action) {
            case 0:
                if(selected > 0) {
                    frame.playSFX(10);
                    selected--;
                }   break;
            case 1:
                if(selected < 3) {
                    frame.playSFX(10);
                    selected++;
                }   break;
            case 2:
                if(selected == 0) {
                    frame.playSFX(11);
                }
                if(selected == 1) {
                    frame.playSFX(11);
                }
                if(selected == 2) {
                    selected = 0;
                    frame.playSFX(17);
                    pc.swapCard("game");
                    frame.playSong(2);
                }
                if(selected == 3) {
                    frame.playSFX(17);
                    pc.swapCard("options");
                }   break;
            case 3:
                keyPressed = false;
                break;
            default:
                break;
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
        selected = 4;
        
        getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed ENTER"), PRESSEDSELECT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("released ENTER"), RELEASEDSELECT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), BACK);
        
        getActionMap().put(MOVE_LEFT, new Action(this, 0));
        getActionMap().put(MOVE_RIGHT, new Action(this, 1));
        getActionMap().put(PRESSEDSELECT, new Action(this, 2));
        getActionMap().put(RELEASEDSELECT, new Action(this, 3));
        getActionMap().put(BACK, new Action(this, 4));
    }
    
    public void navigate(int action) {
        switch(action) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                pc.swapCard("main");
                frame.playSFX(15);
                break;
            default:
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
        selected = 5;
        
        getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed ENTER"), PRESSEDSELECT);
        
        getActionMap().put(MOVE_UP, new Action(this, 0));
        getActionMap().put(MOVE_DOWN, new Action(this, 1));
        getActionMap().put(PRESSEDSELECT, new Action(this, 2));
    }
    
    public void navigate(int action) {
        switch(action) {
            case 0:
                if(selected > 5) {
                    selected--;
                    frame.playSFX(10);
                }   break;
            case 1:
                if(selected < 6) {
                    selected++;
                    frame.playSFX(10);
                }   break;
            case 2:
                if(selected == 5) {
                    selected = 5;
                    pc.swapCard("game");
                    frame.playSFX(17);
                }
                if(selected == 6) {
                    selected = 5;
                    pc.swapCard("main");
                    frame.playSFX(11);
                    frame.playSong(0);
                }   break;
            default:
                break;
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