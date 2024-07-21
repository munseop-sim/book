package inflearn.javaThread.chapter04;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static inflearn.util.MyLogger.log;
import static inflearn.util.ThreadUtil.sleep;

public class FlagPrintMain {
    public static void main(String[] args){
        FlagPrint flagPrint = new FlagPrint();
        Thread thread = new Thread(flagPrint,"flagPrint");
        thread.start();

        var scanner = new Scanner(System.in);
        while(true){
            log("프린트할 문서 입력(종료:q) : ");
            String str = scanner.nextLine();
            if("q".equals(str)){
                flagPrint.flag = false;
                break;
            }
            flagPrint.addQueue(str);
        }
        log("main exited");
    }

    static class FlagPrint implements Runnable {
        volatile boolean flag = true;
        private Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run(){
            while(flag){
                if(jobQueue.isEmpty()){
                    continue;
                }

                String job = jobQueue.poll();
                log("출력시작 : "+ job + ", 대기문서 : "+ jobQueue);
                sleep(2000);
                log("출력완료");
            }

            log("FlagPrint exited");
        }

        public void addQueue(String str){
            jobQueue.add(str);
        }

    }
}
