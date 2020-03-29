package nl.elec332.lib.java.swing;

import java.awt.*;

/**
 * Created by Elec332 on 20-3-2020
 */
public class LinedGridBagConstraints extends GridBagConstraints {

    public LinedGridBagConstraints(int line) {
        this(line, 1);
    }

    public LinedGridBagConstraints(int line, int height) {
        this.gridy = line;
        this.gridheight = height;
    }

    public LinedGridBagConstraints alignLeft() {
        this.gridx = 0;
        this.weightx = 0.75;
        this.anchor = WEST;
        return this;
    }

    public LinedGridBagConstraints alignRight() {
        this.gridx = 0;
        this.weightx = 0.75;
        this.anchor = EAST;
        return this;
    }

    public LinedGridBagConstraints alignBottom() {
        this.anchor = SOUTH;
        this.weighty = 0.75;
        return this;
    }

    public LinedGridBagConstraints fillHorizontal() {
        this.fill = HORIZONTAL;
        return this;
    }

}
