package nl.elec332.lib.java.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Elec332 on 9-3-2017.
 */
public class JMenuPanel extends JPanel {

    public JMenuPanel(){
        super(new BorderLayout(5, 100));
        add(left = new JPanel(layout1 = new GridLayout(1, 1)), BorderLayout.WEST);
        add(right = new JPanel(layout2 = new GridLayout(rows, 1)), BorderLayout.EAST);
        ((GridLayout) left.getLayout()).setHgap(20);
    }

    private JPanel left, right;
    private GridLayout layout1, layout2;
    private int rows;

    public <C extends Component> C addMenuEntry(String desc, C component){
        addRow();
        left.add(new JLabel("  " + desc));
        ((JPanel) right.add(new JPanel(new BorderLayout()))).add(component, BorderLayout.WEST);
        return component;
    }

    private void addEmptyRow(){
        addMenuEntry("", new JLabel(""));
    }

    private void addRow(){
        rows++;
        layout1.setRows(rows);
        layout2.setRows(rows);
    }

}