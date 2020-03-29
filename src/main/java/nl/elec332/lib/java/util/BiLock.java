package nl.elec332.lib.java.util;

import java.util.function.BooleanSupplier;

/**
 * Created by Elec332 on 1-2-2020
 */
public class BiLock {

    public void waitForCondition1(BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void waitForCondition2(BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public synchronized void notifyChange() {
        notifyAll();
    }

}
