package chapter02;

import static util.MyLogger.log;

public class HelloRunnableMain3 {

    public static void main(String[] args) {
        log("main() start");
        log("start 호출 전");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    log("run()");
                }
            }
        });
        thread.start();

        log("start 호출 후");
        log("main() end");
    }
}
