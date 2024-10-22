package inflearn.javaThread.chapter05;

import static inflearn.util.MyLogger.log;
import static inflearn.util.ThreadUtil.sleep;

public class UnVolatileMain {
    public static void main(String[] args) {
        log("Main start");
        UnVolatileTask unVolatile = new UnVolatileTask();
        Thread unVolatileTask = new Thread(unVolatile);
        log("UnVolatileTask Thread start");
        unVolatileTask.start();
        sleep(1000);

        unVolatile.setRunning(false);
        log("UnVolatileTask isRunning = false");
        log("UnVolatileTask.isRunning() = " + unVolatile.isRunning());
        log("Main end");

    }

    static class UnVolatileTask implements Runnable{
        private boolean isRunning = true;
        @Override
        public void run() {
            log("UnVolatileTask start");
            while (isRunning) {
                // do something
            }
            log("UnVolatileTask end");
        }

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }
    }


}
