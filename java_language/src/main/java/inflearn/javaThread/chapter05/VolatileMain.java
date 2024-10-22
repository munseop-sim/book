package inflearn.javaThread.chapter05;

import static inflearn.util.MyLogger.log;
import static inflearn.util.ThreadUtil.sleep;

public class VolatileMain {
    public static void main(String[] args) {
        log("Main start");
        VolatileTask unVolatile = new VolatileTask();
        Thread ㅍolatileTask = new Thread(unVolatile);
        log("VolatileTask Thread start");
        ㅍolatileTask.start();
        sleep(1000);

        unVolatile.setRunning(false);
        log("VolatileTask isRunning = false");
        log("VolatileTask.isRunning() = " + unVolatile.isRunning());
        log("Main end");

    }

    static class VolatileTask implements Runnable{
        private volatile boolean isRunning = true;
        @Override
        public void run() {
            log("VolatileTask start");
            while (isRunning) {
                // do something
            }
            log("VolatileTask end");
        }

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }
    }


}
