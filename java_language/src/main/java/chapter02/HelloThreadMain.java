package chapter02;

public class HelloThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread t1 = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start 호출 전");
        t1.start(); //run()이 아닌 start()를 호출해야 새로운 스레드가 생성되고 run()이 실행된다. 만약 run()을 실행할경우 메인스레드 스택에서 실행된다.(별도의 스레드가 아님)
        System.out.println(Thread.currentThread().getName() + ": start 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class HelloThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");
        }
    }

}
