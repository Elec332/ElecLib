package nl.elec332.lib.java.swing;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Elec332 on 13-8-2019
 */
public class SwingHelper {

    public static void setEnabledAll(Component component, boolean enabled) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component c : ((Container) component).getComponents()) {
                setEnabledAll(c, enabled);
            }
        }
    }

    @SuppressWarnings("all")
    public static <C extends AbstractButton> void addListener(C component, Consumer<C> listener){
        component.addActionListener(e -> listener.accept((C) e.getSource()));
    }

    @SuppressWarnings("all")
    public static <C extends JComponent> C addComponent(JComponent parent, C child, Consumer<C>... modifiers){
        for (Consumer<C> modifier : modifiers){
            if (modifier != null){
                modifier.accept(child);
            }
        }
        parent.add(child);
        return child;
    }

    public static JLabel createAutoUpdatingLabel(final long time, final Supplier<Object> data){
        JLabel ret = new JLabel();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(time);
                    EventQueue.invokeLater(() -> ret.setText(data.get().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return ret;
    }

}
