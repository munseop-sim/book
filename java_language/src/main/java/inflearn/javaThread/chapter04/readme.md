# chapter04. 스레드 제어(Interrupt, yield)와 생명주기

### 스레드 중지
1. flag 를 이용한 스레드 중지 ([FlagThreadMain](FlagPrintMain.java))
2. interrupt()를 이용한 스레드 중지([InterruptThreadMain](InterruptPrintMain.java))
   - `Thread.interrupted()` : 현재 스레드의 인터럽트 상태를 반환하고, **상태를 초기화**한다.
   - `Thread.currentThread().isInterrupted()` : 현재 스레드의 인터럽트 상태를 반환한다.

### 스레드 양보 ([InterruptYieldPrintMain](InterruptYieldPrintMain.java))
1. `Thread.yeild()`메소드는 현재 실행중인 스레드가 자발적으로 CPU를 양보하여 다른 스레드가 실행될 수 있도록 한다.
2. `yeild()`메소드를 호출한 스레드는 RUNNABLE 상태를 유지하면서 CPU를 양보한다. 즉, 이스레드는 다시 스케쥴링 큐에 들어가면서 다른 스레드에게 CPU사용기회를 넘긴다.
3. `yeild()`메소드는 다른 스레드에게 CPU사용기회를 넘기지만, 다른 스레드가 바로 실행되는 것을 보장하지 않는다.(운영체제의 스케쥴러에게 힌트를 제공할뿐 강제되지는 않는다.)