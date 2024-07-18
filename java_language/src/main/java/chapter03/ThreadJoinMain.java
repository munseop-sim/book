package chapter03;

import static util.MyLogger.log;

public class ThreadJoinMain {

    public static void main(String[] args) throws InterruptedException {
        log("Main started");
        var sumTask1 = new SumRunnable(1, 50, "Task1");
        var sumTask2 = new SumRunnable(51, 100, "Task2");
        var sumRunnable1 = new Thread(sumTask1);
        var sumRunnable2 = new Thread(sumTask2);

        log("sumRunnable1, sumRunnable2 started");
        sumRunnable1.start();
        sumRunnable2.start();

        log("sumRunnable1, sumRunnable2 join started");
        //join 생략시에 원하는 결과인 5050이 나오지 않음
        sumRunnable1.join();
        sumRunnable2.join();
        log("sumRunnable1, sumRunnable2 join ended");

        var sum = sumTask1.getSum() + sumTask2.getSum();
        log("Sum = " + sum);
    }

    static class SumRunnable implements Runnable {
        private int sum;
        private int start;
        private int end;
        private String threadName;

        public SumRunnable(int start, int end, String threadName) {
            this.start = start;
            this.end = end;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            log("Thread " + threadName + " started");
            for (int i = start; i <= end; i++) {
                sum += i;
            }

            log("Thread " + threadName + " ended");
        }

        public int getSum() {
            return sum;
        }
    }

}
