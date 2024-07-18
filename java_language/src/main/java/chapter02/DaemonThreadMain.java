package chapter02;

public class DaemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        var daemonThread = new DaemonThread();
        System.out.println(Thread.currentThread().getName() + ": start 호출 전");

        daemonThread.setDaemon(false);
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + ": start 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run() start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": run() end");
        }

    }
}
