package inflearn.javaThread.chapter04;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static inflearn.util.MyLogger.log;

public class InterruptYieldPrintMain {
    public static void main(String[] args){
        InterruptPrint interruptPrint = new InterruptPrint();
        Thread thread = new Thread(interruptPrint,"interruptPrint");
        thread.start();

        var scanner = new Scanner(System.in);
        while(true){
            log("프린트할 문서 입력(종료:q) : ");
            String str = scanner.nextLine();
            if("q".equals(str)){
                thread.interrupt();
                break;
            }
            interruptPrint.addQueue(str);
        }
        log("main exited");
    }

    static class InterruptPrint implements Runnable {

        private Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run(){

            try{
                while(!Thread.interrupted()){
                    if(jobQueue.isEmpty()){
                        Thread.yield();
                        continue;
                    }

                    String job = jobQueue.poll();
                    log("출력시작 : "+ job + ", 대기문서 : "+ jobQueue);

                    Thread.sleep(2000);

                    log("출력완료");
                }
            }catch (InterruptedException ex){
                log("Interrupted occurred -> "+ex.getMessage());
                log("Current Thread interrupted : "+Thread.currentThread().isInterrupted());
            }
            log("FlagPrint exited");
        }

        public void addQueue(String str){
            jobQueue.add(str);
        }

    }
}
