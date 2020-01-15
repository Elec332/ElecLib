package nl.elec332.lib.jfreechart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;

/**
 * Created by Elec332 on 7-11-2019
 */
public class Charts {

    public static void showChart(final JFreeChart chart) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(chart.getTitle().getText());
            ChartPanel pan = new ChartPanel(chart);
            frame.setContentPane(pan);
            frame.setAlwaysOnTop(true);
            frame.pack();
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }

}