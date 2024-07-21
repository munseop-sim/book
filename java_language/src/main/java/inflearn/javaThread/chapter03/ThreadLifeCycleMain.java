package inflearn.javaThread.chapter03;

import static inflearn.util.MyLogger.log;

public class ThreadLifeCycleMain {

    public static void main(String[] args) throws InterruptedException {
        var myThread = new Thread(new MyThread(), "myThread");
        log("myThread state1 = "+myThread.getState()); // NEW
        log("start myThread");
        myThread.start();
        Thread.sleep(1000);
        log("myThread state3 = "+myThread.getState()); // TIME_WAITING
        Thread.sleep(4000);
        log("myThread state5 = "+myThread.getState()); // TERMINATED
        log("end main");

    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                var currentThread = Thread.currentThread();
                log("start");

                log("myThread state2 = "+currentThread.getState()); // RUNNABLE
                log(" myThread sleeping for 3 seconds");

                Thread.sleep(3000);
                log(" myThread sleeping ended");


                log("myThread state4 = "+currentThread.getState()); // RUNNABLE
                log("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
