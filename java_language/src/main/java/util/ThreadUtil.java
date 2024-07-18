package util;

import static util.MyLogger.log;

public abstract class ThreadUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("Thread interrupted : "+ e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
