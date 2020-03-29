package nl.elec332.lib.java.swing;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Created by Elec332 on 18-3-2020
 */
public class SwingMainHelper {

    public static void startMain(Consumer<String[]> starter, String... args) {
        // Needed cuz MAC is shite...
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        try {
            SwingUtilities.invokeLater(() -> {
                startHandler(starter, args);
            });
        } catch (Exception e) {
            error(e);
        }
    }

    private static final AtomicBoolean stopKA = new AtomicBoolean(false), KAActive = new AtomicBoolean(false);

    private static void error(Throwable e) {
        e.printStackTrace();

        StringBuilder trace = new StringBuilder(e.toString() + "\n\n");
        for (StackTraceElement element : e.getStackTrace()) {
            String s = element.toString();
            if (s.contains("nl.elec332.lib.java.swing.SwingMainHelper.startHandler")) {
                break;
            }
            trace.append(s).append("\n");
        }

        JTextArea jta = new JTextArea(trace.toString());
        JScrollPane jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(600, 400));
        DialogHelper.showErrorMessageDialog(jsp, "Error");
        System.exit(0);
    }

    private static void startHandler(Consumer<String[]> starter, String... args) {
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> error(e));
        startProgram(starter, args);
    }

    private static void startProgram(Consumer<String[]> starter, String... args) {
        starter.accept(args);
    }

    public static void stopKeepAlive() {
        if (!KAActive.get()) {
            return;
        }
        stopKA.set(true);
    }

    public static void preventComputerSleepHack() {
        if (KAActive.get()) {
            return;
        }
        Thread t = new Thread(() -> {
            try {
                Robot hal = new Robot();
                //noinspection ConditionalBreakInInfiniteLoop
                while (true) {
                    if (stopKA.get()) {
                        break;
                    }
                    hal.delay(1000 * 30);
                    Point pObj = MouseInfo.getPointerInfo().getLocation();
                    hal.mouseMove(pObj.x, pObj.y);
                }
            } catch (Exception e) {
                error(e);
            }
            stopKA.set(false);
            KAActive.set(false);
        });
        t.setDaemon(true);
        t.start();
        KAActive.set(true);
    }

}
