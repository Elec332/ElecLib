package nl.elec332.lib.java.swing;

import nl.elec332.lib.java.util.image.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.function.Supplier;

/**
 * Created by Elec332 on 18-3-2020
 */
public class ImagePanel extends JPanel {

    public ImagePanel(ImageManager<?> img, int width, int height) {
        this(img::getImage, width, height);

    }

    public ImagePanel(Supplier<BufferedImage> img, int width, int height) {
        this.img = img;
        this.width = width;
        this.height = height;
        this.scale = 1;
        setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                onResize(getWidth(), getHeight());
            }

        });
        setBackground(Color.BLACK);
    }

    private final Supplier<BufferedImage> img;
    private final double width, height;
    private double scale;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = getSize();
        g.drawImage(img.get(), 0, 0, dim.width, dim.height, this);
    }

    public void onResize(int width, int height) {
        double scale = width / this.width;
        this.scale = Math.min(scale, height / this.height);
    }

}
