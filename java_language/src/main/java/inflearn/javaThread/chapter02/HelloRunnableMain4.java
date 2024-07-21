package inflearn.javaThread.chapter02;

import static inflearn.util.MyLogger.log;

public class HelloRunnableMain4 {

    public static void main(String[] args) {
        log("main() start");
        log("start 호출 전");

        Thread thread = new Thread(() ->{
                log("run()");
            }
        );
        thread.start();

        log("start 호출 후");
        log("main() end");
    }
}
