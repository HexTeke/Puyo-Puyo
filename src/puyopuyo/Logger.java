package puyopuyo;

import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Logger extends JFrame {
    private JScrollPane pane;
    private JList<String> list;
    private DefaultListModel<String> dlModel;
    
    public Logger() {
        super();
        dlModel = new DefaultListModel<>();
        
        list = new JList<>();
        list.setModel(dlModel);
        
        pane = new JScrollPane(list);
        pane.setAutoscrolls(true);
        
        addComponents();
    }
    
    private void addComponents() {
        add(pane);
        addEvent("test");
        setPreferredSize(new Dimension(800, 600));
        setVisible(true);
    }
    
    public void addEvent(String s) {
        dlModel.add(0, s);
    }
}
