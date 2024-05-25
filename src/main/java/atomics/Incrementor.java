package atomics;

import java.util.concurrent.atomic.AtomicInteger;

public class Incrementor {


    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        var t1 = new Thread(Incrementor::increment);
        var t2 = new Thread(Incrementor::increment);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
        long start = System.currentTimeMillis();
        var someObj = new SomeClazz();
        var t3 = new Thread(someObj::businessLogic);
        var t4 = new Thread(someObj::businessLogic2);
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        // expected to be around 4 secs
        System.out.println("Done in :" + (System.currentTimeMillis() - start));
    }

    private static void increment() {

        for (int i = 0; i < 1_000_000; i++) {
            counter.incrementAndGet();
        }
    }

    static class SomeClazz {
        private void businessLogic() {
            try {
                synchronized (lock1) {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("business logic done!");
        }

        private void businessLogic2() {
            try {
                synchronized (lock2) {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("business logic 2 done!");
        }
    }
}
