import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerWithBlockingQueue {

    private static final BlockingQueue<Integer> QUEUE = new PriorityBlockingQueue<>(5);


    public static void main(String[] args) {
        var producer = new Thread(() -> {
            while (true) {
                produce();
            }
        });
        var consumer = new Thread(() -> {
            while (true) {
                consume();
            }
        });
        producer.start();
        consumer.start();
    }

    private static void produce() {
        synchronized (QUEUE) {

            while (QUEUE.size() == 5) {
                try {
                    QUEUE.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int res = ThreadLocalRandom.current().nextInt(100);
            System.out.println("Producing result: " + res);
            QUEUE.add(res);
            System.out.println("QUEUE size: " + QUEUE.size());
            QUEUE.notify();

        }


    }

    private static void consume() {
        synchronized (QUEUE) {
            while (QUEUE.isEmpty()) {
                try {
                    QUEUE.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Consuming result:" + QUEUE.poll());
            QUEUE.notify();
            System.out.println("QUEUE size: " + QUEUE.size());
        }

    }

}
