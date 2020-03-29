package nl.elec332.lib.java.swing;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Elec332 on 18-3-2020
 */
public abstract class AbstractFrame extends JFrame {

    protected final void createFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        String img = getImageIcon();
        if (img != null) {
            ImageIcon i = new ImageIcon(Preconditions.checkNotNull(getClass().getClassLoader().getResource(img)));
            setIconImage(i.getImage());
        }
        addElements();
        setJMenuBar(createMenuBar());
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }

        });
        pack();
        setVisible(true);
    }

    public abstract void addElements();

    @Nullable
    public abstract JMenuBar createMenuBar();

    @Nullable
    public String getImageIcon() {
        return null;
    }

    public void exit() {
        System.exit(0);
    }

}
