# 동시성 II

- 프로그램이 주로 프로세서 연산에 시간을 보낸다면，새로운 하드웨어를 추가해 성능을 높여 테스트를 통과하는 방식이 적합하다.
프로세 연산에 시간을 보내는 프로그램은 스레드를 늘인다고 빨라지지 않는다. CPU 사이클은 한계가 있기 때문이다.
- 프로그램이 주로 I/O 연산에 시간을 보낸다면 동시성이 성능을 높여주기도 한다.
시스템 한쪽이 I/O를 기다리는 동안에 다른 쪽이 뭔가를 처리해 노는 CPU를 효과적으로 활용할 수 있다.

### 스레드 추가하기

### 서버 살펴보기

- 서버가 만드는 스레드 수는 몇 개일까? 코드에서 한계를 명시하지 않으면 이론상으로 JVM이 허용하는 수까지 가능하다.

```java
private static Object s = new Object();
private static int count = 0;

public static void main(String[] args) {
    for(;;){
        new Thread(() -> {
            synchronized(s){
                count += 1;
                System.err.println("New thread #"+ count);
            }
            for(;;){
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    System.err.println(e);
                }
            }
        }).start();
    }
}
```

> 위 코드를 실행 했을 때 필자의 Intelij에서는 2020개의 thread를 만들고 OOM이 발생했다.

### 결론

동시성은 그 자체가 복잡한 문제이므로 다중 스레드 프로그램에서는 단일 책임 원칙이 특히 중요하다.



