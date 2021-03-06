package nl.elec332.lib.java.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Elec332 on 26-8-2019
 */
@SuppressWarnings("all")
public class DialogHelper {

    public static void requestFocusInDialog(JComponent component) {
        component.addAncestorListener(new RequestFocusListener());
    }

    public static void showErrorMessageDialog(Object message, String title) {
        showErrorMessageDialog(null, message, title);
    }

    public static void showErrorMessageDialog(Component parent, Object message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean showDialog(Component parent, Object data, String title) {
        return showDialog(parent, data, title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
    }

    public static int showDialog(Component parent, Object data, String title, int optionType) {
        return JOptionPane.showConfirmDialog(parent, data, title, optionType, JOptionPane.PLAIN_MESSAGE);
    }

    public static <T> T askForInput(Component parent, String title, T message) {
        if (JOptionPane.showOptionDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, null, null) == JOptionPane.DEFAULT_OPTION) {
            return null;
        }
        return message;
    }

    public static String askForInput(Component parent, String title) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.requestFocus();
        textArea.requestFocusInWindow();
        textArea.addAncestorListener(new RequestFocusListener());
        if (askForInput(parent, title, scrollPane) == null) {
            return null;
        }
        return textArea.getText();
    }

}
