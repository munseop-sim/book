package inflearn.javaThread.chapter03;

import static inflearn.util.MyLogger.log;

public class ThreadInformationMain {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        printThreadInfo(mainThread);

        Thread thread1 = new Thread(() -> {
            log("Hello from thread 1");
            printThreadInfo(Thread.currentThread());
        }, "Thread 1");
        thread1.start();
    }

    private static void printThreadInfo(Thread thread){
        log("Thread ID: " + thread.getId());
        log("Thread name: " + thread.getName());
        log("Thread priority: " + thread.getPriority());
        log("Thread state: " + thread.getState());

        log("Thread is alive: " + thread.isAlive());
        log("Thread is daemon: " + thread.isDaemon());
        log("Thread is interrupted: " + thread.isInterrupted());
    }
}
