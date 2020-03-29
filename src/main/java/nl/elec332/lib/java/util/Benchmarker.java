package nl.elec332.lib.java.util;

/**
 * Created by Elec332 on 25-1-2020
 */
public class Benchmarker {

    public Benchmarker(String name, int print) {
        this.name = name;
        this.print = print;
        this.counter = 0;
        this.totalTime = 0;
    }

    public static boolean enabled = false;

    private final String name;
    private final int print;
    private int counter;
    private long time, totalTime;

    public void start() {
        if (!enabled) {
            return;
        }
        counter++;
        if (time > 0) {
            throw new IllegalStateException();
        }
        time = System.currentTimeMillis();
    }

    public void reset() {
        counter--;
        time = 0;
    }

    public void stop() {
        if (!enabled) {
            return;
        }
        if (time <= 0) {
            throw new IllegalArgumentException();
        }
        totalTime += (System.currentTimeMillis() - this.time);
        this.time = -1;

        if (counter >= print) {
            System.out.println(name + " avg: " + totalTime / print);
            counter = 0;
            totalTime = 0;
        }
    }

}
