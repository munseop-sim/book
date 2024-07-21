package inflearn.javaThread.chapter02;

import static inflearn.util.MyLogger.log;

public class HelloRunnableMain2 {

    public static void main(String[] args) {
        log("main() start");
        log("start 호출 전");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    log("run()");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        log("start 호출 후");

        log("main() end");
    }
}
