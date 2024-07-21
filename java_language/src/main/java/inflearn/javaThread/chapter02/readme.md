# chapter02. 스레드 생성과 실행

## 1. 스레드 생성 (Thread 클래스)
- Thread 클래스를 상속받아 run() 메서드를 오버라이딩하여 스레드를 생성할 수 있음
- start() 메서드를 호출하여 스레드를 실행함 (run() 메서드를 호출하면 안됨)
- Thread 클래스를 상속받아 스레드를 구현하는 방법은 실무에서는 거의 사용되지 않음

## 2. 스레드 생성 (Runnable 인터페이스)
- Runnable 인터페이스를 구현하여 스레드를 생성 (실무에서 주로 사용)
- Runnable 인터페이스를 구현하는 방식이 스레드와 싱행할 작업을 명확이 분리하고 인터페이스를 사용하므로 Thread클래스를 직접 상속하는 방식보다 더 유연하고 유지보수하기 쉬운 코드를 작성할 수 있다.
- Runnable 인터페이스를 만드는 방법
    1. 정적 중첩 클래스 사용 [HelloRunnableMain1](HelloRunnableMain1.java)
    2. 익명 클래스 사용 [HelloRunnableMain2](HelloRunnableMain2.java)
    3. 익명 클래스를 변수 없이 직접 전달 [HelloRunnableMain3](HelloRunnableMain3.java)
    4. 람다식 사용 [HelloRunnableMain4](HelloRunnableMain4.java)

## 3. 데몬 스레드
- 데몬 스레드는 주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드
- 주 스레드가 종료되면 데몬 스레드는 강제적으로 종료됨
- 데몬 스레드는 주 스레드가 종료되면 더 이상 실행할 필요가 없는 작업을 수행하는 스레드에 사용됨
- `setDaemon(true)` 메서드를 호출하여 데몬 스레드로 설정할 수 있음

