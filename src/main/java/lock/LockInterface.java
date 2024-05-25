package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterface {
    private static final ReentrantLock REENTRANT_LOCK1 = new ReentrantLock();
    private static final ReentrantLock REENTRANT_LOCK2 = new ReentrantLock();
    private static final Object LOCK1 = new Object();
    private static int counter = 0;


    public static void main(String[] args) {
        recursiveDeadlock();
//        new Thread(() -> businessLogic2()).start();
    }


    private static void businessLogic1() {
        try {
            Thread.sleep(500);
            if (REENTRANT_LOCK1.tryLock()) {
                if (REENTRANT_LOCK2.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("hello from thread:" + Thread.currentThread().getName());
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void businessLogic2() {
        try {
            Thread.sleep(500);
            if (REENTRANT_LOCK2.tryLock()) {
                if (REENTRANT_LOCK1.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("hello from thread:" + Thread.currentThread().getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized static void recursiveDeadlock() {
        if (counter == 2) {
            return;
        }
        counter += 1;
        recursiveDeadlock();
    }
}
