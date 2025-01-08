## 활용사례
- cache
- session store : ttl 이용
- pub/sub
- message queue
- geospatial : 지리정보 질의
- leader board : `sorted set`

## 특징
- in-memory
- persistent on Disk 
  - RDB(Snapshot)
  - AOF(Append Only File)
- key/value store
- data types : String, List, Hashe, Set, SortedSet, Geospatial
- Single Thread

## redis-cli

## data types
- 통상적으로 `:`으로 Context를 구분하여 키를 지정한다.
- key 관련 명령어
  - ttl
    - expire [key] [second] : ttl 지정
    - ttl [key] : 남은시간 확인, 만료시에 음수로 나타남
  - del : 동기적 삭제
  - unlink : 비동기삭제 -> 운영시에는 이걸로 삭제해야함
- redis의 `Key/Value`구조에서 Value의 타입을 말한다.
  1. String
     - 대표 기본타입, 바이너리, 문자 데이터 저장, 원자적 값 가감에 대해서 사용
     - command : SET, SETNX, GET, MGET, INC, DEC
  2. List
     - Linked List, Queue, Stack
     - command : lpush, rpush, lpop, rpop, llen, lrange
     - stack: `rpop, rpush` or `lpop, lpush`
     - queue : `rpush, lpop` or `lpush, rpop`
  3. Set 
     - Unordered collection, unique item
     - command: sadd, srem, sismember, smembers, sinter, scard
  4. Sorted Set
     - ordered collection
     - 활용 : leader board, rate limit
     - command : zadd, zrem, zrange, zcard, zrank, zrevrank, zincrby
     - option : `byscore`, `byscore rev`
  5. Hash
     - filed-value pair collections (ex, java에서 hashmap)
     - hset, hget, hmget, hgetall, hdel, hincrby
  6. geospatial : 지리정보
  7. bitmap 
     - 0또는 1의 값으로 이루어진 비트열 (메모리를 적게 사용하여 대량의 데이터 저장에 유용)
     - command : setbit, getbit, bitcount
 
## redis transaction
- command
  - multi: 트랜잭션의 시작을 의미
  - exec: commit
  - discard: rollback
  - watch
    - 동시성제어
    - watch 명령후에 `multi`(트랜잭션시작)를 수행하게 되었을때, 해당 (A)트랜잭션 종료이전에 다른 (B)트랜잭션에서 해당 키에 대한 값이 변경되었을 경우 (A)트랜잭션 내의 설정된 데이터는 무시된다.  



## docker redis 설치
1. docker pull redis image : `docker pull redis`
   - 버전을 명시하지 않으면 latest 버전 pulling
2. docker run : `docker run -p 16379:6370 -name local-redis -d redis`
    - `-p 16379:6379` : 로컬의 16379포트를 도커 컨테이너 내부의 6479포트와 바인딩
    - `-name local-redis` : 컨테이너의 이름을 `local-redis`로 지정
    - `-d` : daemon 모드로 실행
    - `redis` : pulling한 이미지 지정
3. cli 접속
   - `docker ps` 명령어로 실행중인 레디스 컨테이너 아이디 조회
   - `docker exec -it [컨테이너 아이디] redis-cli`
4. monitoring
   - `docker exec -it [컨테이너 아이디] redis-cli monitor` : 실행되는 명령어에 대한 모니터링

## redis 키 검색
- scan : 중복되어 키가 검색될 수는 있지만 cursor 기반으로 검색되기 때문에 다른 작업에 레디스 자체에 부하가 덜 걸린다. 
- keys : 가급적 사용을 지양해야 한다. 레디스는 싱글 스레드로 동작하기 때문에 key 전체를 검색하므로 다른 요청작업이 블로킹된다.
[redis keys vs scan 참고자료](https://velog.io/@sejinkim/Redis-KEYS-vs-SCAN))

## redis의 pub/sub
- command : publish, subscribe, pubsub, psubscribe(pattern subscribe)
- spring에서의 Pub/Sub
  - subscribe
    - MessageListenerAdapter, RedisMessageListenerContainer 활용
      ```java
      //config
      @Configuration
      public class RedisConfig {
    
          @Bean
          MessageListenerAdapter messageListenerAdapter() {
              return new MessageListenerAdapter(new MessageListenService());
          }
    
          @Bean
          RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                      MessageListenerAdapter listener) {
              RedisMessageListenerContainer container = new RedisMessageListenerContainer();
              container.setConnectionFactory(connectionFactory);
              container.addMessageListener(listener, ChannelTopic.of("[토픽명]"));
              return container;
          }
      }
      
      //listen
      @Service
      public class MessageListenService implements MessageListener {
        
            @Override
            public void onMessage(Message message, byte[] pattern) {
                log.info("Received {} channel: {}", new String(message.getChannel()), new String(message.getBody()));
            }
      }
      ```
  - publish 
    - redisTemplate.convertAndSend
      ```java
          redisTemplate.convertAndSend("토픽명", "발행메세지");
      ```

## redis replication
- redis config를 통하여 replication을 설정할 수 있다.
1. replication
   - eplication은 하나의 Redis 인스턴스(마스터)가 쓰기 작업을 처리하고, 하나 이상의 복제본(슬레이브)이 마스터의 데이터를 복제하여 읽기 작업을 처리하는 구조입니다. 이를 통해 읽기 성능을 확장하고, 데이터 백업을 유지할 수 있습니다. 복제는 비동기적으로 이루어지며, 마스터에 기록된 데이터는 슬레이브로 전파됩니다. 그러나 마스터 장애 시 자동으로 슬레이브를 승격하는 기능은 기본적으로 제공되지 않으며, **수동으로 조치**해야 합니다.
   1. master-replicas
   2. command stream
   3. resync
   4. slave의 master승격은 사용자가 처리해야 한다.
2. sentinel
   - Sentinel은 Redis의 고가용성을 지원하는 도구로, 마스터와 슬레이브 인스턴스를 모니터링하고, 마스터 장애 시 자동으로 슬레이브를 새로운 마스터로 승격시키는 기능을 제공합니다. Sentinel은 분산 시스템으로 구성되며, 여러 Sentinel 인스턴스가 협력하여 장애를 감지하고 복구합니다. 이를 통해 자동 장애 조치와 구성 관리가 가능해집니다. 
   1. monitoring
   2. automatic failover
[Redis(Replication, Cluster, Sentinel)](https://www.essential2189.dev/what-is-redis)

## redis monitoring
- prometheus, grafana, redis exporter의 구성으로 redis command 명령어가 아닌 monitoring 시스템을 구축할 수 있다. 
- 또한 redis에 메모리제한 기능을 설정 할 수 있고, 메모리가 가득 찼을때 키를 삭제하는 알고리즘을 선택하여 설정할 수 있다.
- maxmemory-policy
  - noeviction
  - **allkeys-lru (least recently used)** :: 일반적으로 가장 많이 사용하는 알고리즘
  - allkeys-lfu (least frequently used)
  - volatile-lru
  - volatile-lfu
  - allkeys-random
  - volatile-random
- [docker-compose 를 활용한 redis monitoring 설정](https://github.com/morenice/fastcampus-2023-backend-advacned/tree/main/ch2/clip19)
- [redis-exporter 설정](https://velog.io/@akeka0303/Redis-Exporter-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)
## 5. tool
- redis-insight: `https://redis.io/insight/`
- redis-exporter : `https://github.com/oliver006/redis_exporter`