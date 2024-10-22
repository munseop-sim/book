# chapter06, 07. 동기화 (synchronized, concurrent.Lock)

## 1. synchronized
- 동기화()는 여러 스레드가 하나의 자원을 사용할 때, 그 자원에 대한 접근을 제한하는 것을 말한다.
  - 동시성 문제는 volatile 키워드로 해결이 불가하다. volatile 키워드는 스레드 값이 변경되었을 때, 변경된 값을 즉시 볼수 있게 하는 메모리 가시성의 문제를 해결할 뿐이다.
  - volatile 키워드를 사용하지 않아도 synchronized 안에서 접근하는 변수의 메모리 가시성 문제는 해결된다.
  - 동기화를 사용하면 다음과 같은 문제들을 해결할 수 있다.
    - 경합조건(Race Condition) : 두 개 이상의 스레드가 경쟁적으로 동일한 자원을 수정할 때 발생하는 문제
    - 데이터 일관성 : 여러 스레드가 동시에 읽고 쓰는 데이터의 일관성을 유지
  - 동기화는 멀티스레드 환경에서는 필수적인 기능이지만, 과도하게 사용될 경우, 성능저하를 초래할 수 있으므로 꼭 필요한 곳에 적절히 사용해야 한다.


### 임계영역(Critical Section)
- 여러 스레드가 동시에 접급하면 데이터 불일치나 예상치 못한 동작이 발생할 수 있는 위험하고 또 중요한 코드부분을 뜻한다.
  - 동기화는 임계영역(Critical Section)을 설정하여 여러 스레드가 동시에 접근하지 못하도록 한다.
  - 자바에서는 synchronized 키워드를 사용하여 임계영역을 보호하여 동기화를 구현한다.


### synchronized 키워드
- synchronized 키워드는 메소드나 블록에 사용할 수 있다.
  - synchronized 키워드를 사용하면 해당 메소드나 블록을 임계영역으로 설정하여 여러 스레드가 동시에 접근하지 못하도록 한다.
  - synchronized 키워드를 사용하면 임계영역에 들어가기 전에는 락을 획득하고, 임계영역을 빠져나오면 락을 반납한다.
  - 락을 획득하지 못한 스레드는 `BLOCKED` 상태가 되어 다른 스레드가 락을 반납할 때까지 기다린다.


### synchronized 키워드 단점
- 무한대기
  - 중간에 해당 스레드에 인터럽트가 발생해도 락을 반납하지 않고 계속 무한대기 상태에 빠질 수 있다.
- 공정성
  - `synchronized`의 가장 치명적인 단점은 락을 얻기 위해 무한대기 상태에 빠질 수 있다는 것이다.
  - 락이 돌아왔을때 BLOCKED 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할지 알 수가 없다.
  - 이와 같은 문제를 해결하기 위해 자바 1.5부터 `java.util.concurrent` 패키지에서 `Lock` 인터페이스와 `ReentrantLock` 클래스를 제공한다.


## 2. ReentrantLock
### LockSupport
- `park()` : 스레드를 WAITING 상태로 변경한다.
- `parkNonos(nanos)` : 스레드를 지정된 nano시간만큼 대기시킨다.
  - 지정한 nano시간이 경과되면 `TIMED_WATING` 상태에서 `RUNNABLE`상태로 변경된다.
- `unpark(thread)` : `park()`로 `WAITING` 상태에 있는 스레드를 `RUNNABLE` 상태로 변경한다.
  - 스레드가 이미 `RUNNABLE` 상태에 있으면 아무런 효과가 없다.

### `대기 상태(WAITING)`와 `시간 대기상태(TIME_WAITING)`
- Thread.join(), Thread.join(long millis)
- Thread.park(), Thread.parkNanos(long nanos)
- Object.wait(), Object.wait(long millis)

### LockSupport의 단점
- `LockSupoort`는 `synchronized`키워드의 단점인 무한대기, 공정성을 극복할 수는 있지만 너무 저수준이다. `synchronized`처럼 고수준의 기능이 필요하다. 물론 직접 구현은 가능하나 매우 어렵다.
  - 10개의 스레드중에 1개의 스레만 락을 획득하게 하고 해당 스레드 종료시에 락을 반환하는 등의 로직을 담는 등의 기능을 포함해야함.
- 이를 극복하기 위해서 `Lock`인터페이스와 `ReentrantLock`구현체를 제공한다. `ReentrantLock`은 `LockSupport`를 활용해서 `synchronized`의 단점을 극복하면서도 편리하게 임계영역을 다룰 수 있다.

### `Lock` 인터페이스
- `void lock()`
- 락 획득, 이미 다른 스레드가 락을 획득했다면, 락이 풀릴때까지 현재 스레드는 `WAITING`하며, 이 메서드는 인터럽트에 응답하지 않는다.
- 여기서 사용하는락은 객체 내부에 있는 모니터락이 아니다. Lock 인터페이스와 ReentrantLock이 제공하는 기능이다.
  - 모니터락과 `BLOCKED`상태는 `synchronized`에서만 사용된다.
  - [모니터락이란](https://happy-coding-day.tistory.com/entry/JAVA-%EB%AA%A8%EB%8B%88%ED%84%B0%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)
- `void lockInterruptibly()`
- `boolean tryLock()`
- `boolean tryLock(long time, TimeUnit unit)`
- `void unlock()`
- `Condition newCondition()`