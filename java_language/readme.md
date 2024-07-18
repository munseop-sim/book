# 인프런 - 김영한의 실전 자바 - 고급 1편, 멀티스레드와 동시성 강의 내용 정리


[1. 프로세스와 스레드](./src/main/java/chapter01/readme.md)
[2. 스레드 생성과 실행](./src/main/java/chapter02/readme.md)




## util package
1. MyLogger : Slf4j와 유사한 로그 출력을 위한 클래스 
2. ThreadUtil : 스레드 생성 및 실행을 위한 유틸리티 클래스
   1. `sleep(int ms)` : 전달되는 ms(밀리초)만큼 대기, run 메소드내에서 학습용으로 sleep을 사용할 때 사용
      - `Thread.sleep(ms)`를 호출하면 `InterruptedException`이 발생할 수 있으므로 `InterruptedException`을 처리하기 위한 RuntimeException 을 발생싴킴
      - Runnable 인터페이스는 run 메소드에서 체크예외를 던질 수 없으므로 `RuntimeException`을 던짐
      - Runnable 코드
        ```java
        @FunctionalInterface
        public interface Runnable {
            /**
            * Runs this operation.
            */
            void run();
        }
        ```
