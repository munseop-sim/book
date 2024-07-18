package chapter02;

import static util.MyLogger.log;

public class HelloRunnableMain1 {

    public static void main(String[] args) {
        log("main() start");
        log("start 호출 전");

        Thread thread = new Thread(new HelloRunnable());
        thread.start();

        log("start 호출 후");
        log("main() end");
    }


    static class HelloRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");
        }
    }
}
