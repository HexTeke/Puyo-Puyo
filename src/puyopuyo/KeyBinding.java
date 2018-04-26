package puyopuyo;

import java.awt.event.KeyEvent;

public class KeyBinding {
    public int up, down, left, right;
    public int rotClock, rotCounter;
    
    public int pause, select;
    
    public KeyBinding()
    {
        up = KeyEvent.VK_UP;
        down = KeyEvent.VK_DOWN;
        left = KeyEvent.VK_LEFT;
        right = KeyEvent.VK_RIGHT;
        
        rotClock = KeyEvent.VK_X;
        rotCounter = KeyEvent.VK_Z;

        pause = KeyEvent.VK_ESCAPE;
        select = KeyEvent.VK_ENTER;
    }
}
