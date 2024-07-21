package inflearn.util;

public abstract class ThreadUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            MyLogger.log("Thread interrupted : "+ e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
