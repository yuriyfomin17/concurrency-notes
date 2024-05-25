package condition.variable;

public class WaitNotifyExample {

    private static final Object lock1 = new Object();
    private static int counter = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    lock1.wait();
                    counter += 1;
                    System.out.println("Queue 1 Counter: " + counter);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    lock1.wait();
                    counter += 1;
                    System.out.println("Queue 2 Counter: " + counter);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        new Thread(() -> {
            synchronized (lock1) {
                lock1.notify();
                lock1.notify();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter += 1;

                System.out.println("No queue woke up previous thread");
            }
        }).start();
    }
}
