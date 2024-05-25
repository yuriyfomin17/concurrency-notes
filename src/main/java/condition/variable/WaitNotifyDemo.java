package condition.variable;

public class WaitNotifyDemo {

    private static final Object lock1 = new Object();

    public static void main(String[] args) throws Exception {

        var thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("I am waiting!");
                    lock1.wait();
                    System.out.println("Finished waiting!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("notified");
                lock1.notifyAll();
            }
        });
        System.out.println(thread1.getPriority());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        ThreadGroup threadGroup = new ThreadGroup("");

    }


}
