import java.util.ArrayDeque;
import java.util.Queue;

public class ConditionVariable {

    private static final Queue<Integer> QUEUE = new ArrayDeque<>();
    private static final int BUFFER_SIZE = 5;

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            while (true) {
                produce();
            }
        });
        Thread consumerThread = new Thread(() -> {
            while (true) {
                consume();
            }
        });
        producerThread.start();
        consumerThread.start();
    }

    private static void consume() {
        synchronized (QUEUE) {
            while (QUEUE.isEmpty()) {
                System.out.println("Queue is empty notified");
                try {
                    QUEUE.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("consumed resource: " + QUEUE.poll());
            QUEUE.notify();
        }
    }

    private static void produce() {
        synchronized (QUEUE) {
            while (QUEUE.size() == BUFFER_SIZE) {
                try {
                    System.out.println("waiting since queue is full");
                    QUEUE.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int res = (int) (Math.random() * (100));
            System.out.println("Producing number integer:" + res);
            QUEUE.add(res);
            QUEUE.notify();
        }
    }
}