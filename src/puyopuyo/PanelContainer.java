package puyopuyo;

import java.awt.CardLayout;
import java.awt.Container;

/*  Key Mappings:
 *  https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyEvent.html
 */

public class PanelContainer {
    private final PuyoPuyo frame;
    private final CardLayout card;
    private final Container c;
    
    private final Menu main;
    private final Menu options;
    
    public PanelContainer(PuyoPuyo p) {
        super();
        frame = p;
        card = new CardLayout();
        c = frame.getContentPane();
        c.setLayout(card);
        
        main = new MainMenu(frame, this);
        options = new OptionsMenu(frame, this);
        
        c.add(main, "main");
        c.add(options, "options");
    }

    public void swapCard(String name) {
        card.show(c, name);
    }
}

