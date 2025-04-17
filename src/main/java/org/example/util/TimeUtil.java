package org.example.util;

public class TimeUtil {
    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sleepMilleSeconds(int milleSeconds) {
        try {
            Thread.sleep(milleSeconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
