package psjava.practice;

public class ThreadEg {

    public static void main(String[] args) {
        // *스레드의 실행은 run() 호출이 아닌 start() 호출로 해야한다.*
        // 스레드를 이용한다는 건, JVM이 다수의 콜 스택을 번갈아가며 일처리를 하고 사용자는 동시에 작업하는 것처럼 보여준다.
        // run() 메소드를 이용한다는 것은 main()의 콜 스택 하나만 이용하는 것으로 스레드 활용이 아니다.
        // start()는 스레드가 작업을 실행하는데 필요한 콜 스택을 생성한 다음, run()을 호출해서 그 스택 안에 run()을 저장할 수 있도록 해준다.

        // Thread 클래스를 상속받은 경우는, 상속받은 클래스 자체를 스레드로 사용할 수 있다.
        Thread t = new ExtendedThread("extended");
        t.start();

        Runnable t2 = new RunnableImpl("runnable");
    }
}

class ExtendedThread extends Thread {

    private ExtendedThread() {
    }

    public ExtendedThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            /*
             * 스레드의 상태는 5가지가 있다
             * - NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
             * - RUNNABLE : 실행 중 또는 실행 가능 상태
             * - BLOCKED : 동기화 블럭에 의해 일시정지된 상태(lock이 풀릴 때까지 기다림)
             * - WAITING, TIME_WAITING : 실행가능하지 않은 일시정지 상태
             * - TERMINATED : 스레드 작업이 종료된 상태
             */

            /*
             * 동기화
             * - 멀티스레드로 구현을 하면, 동기화는 필수적
             * - 동기화가 필요한 이유? 여러 스레드가 같은 프로세스 내의 자원을 공유하면서 작업할 때 서로의 작업이 다른 작업에 영향을 주기 때문
             * - 스레드의 동기화를 위해선, 임계 영역(critical section)과 잠금(lock)을 활용
             * - _임계영역_을 지정하고, 임계영역을 가지고 있는 lock을 _단 하나의 스레드_에게만 빌려주는 개념
             * => 따라서 임계구역 안에서 수행할 코드가 완료되면, lock을 반납해줘야 함
             *
             * 스레드 동기화 방법
             * - 임계 영역(critical section) : 공유 자원에 단 하나의 스레드만 접근하도록(하나의 프로세스에 속한 스레드만 가능)
             * - 뮤텍스(mutex) : 공유 자원에 단 하나의 스레드만 접근하도록(서로 다른 프로세스에 속한 스레드도 가능)
             * - 이벤트(event) : 특정한 사건 발생을 다른 스레드에게 알림
             * - 세마포어(semaphore) : 한정된 개수의 자원을 여러 스레드가 사용하려고 할 때 접근 제한
             * - 대기 가능 타이머(waitable timer) : 특정 시간이 되면 대기 중이던 스레드 깨움
             */
            System.out.println(i + ":" + Thread.currentThread().getName() + ", " + Thread.currentThread().getState());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RunnableImpl implements Runnable {

    private RunnableImpl() {
    }

    public RunnableImpl(String name) {
        Thread t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ":" + Thread.currentThread().getName() + ", " + Thread.currentThread().getState());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
