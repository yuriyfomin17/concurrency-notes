package thread.pool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;

public class ThreadPoolBlockingQueueExample {

    private static final Queue<Runnable> QUEUE = new ArrayBlockingQueue<>(5);
    private static final LinkedList<Thread> WORKERS = new LinkedList<>();

    public static void main(String[] args) {
        ThreadPoolBlockingQueue threadPoolBlockingQueue = new ThreadPoolBlockingQueue(5);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);

        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
        threadPoolBlockingQueue.execute(ThreadPoolBlockingQueueExample::businessLogic);
    }

    private static void businessLogic(){
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello from thread:" + Thread.currentThread().getName());
    }

    static class ThreadPoolBlockingQueue implements Executor {

        public ThreadPoolBlockingQueue(int capacity){
            for (int i = 0; i < capacity; i++) {
                Thread worker = new Worker();
                worker.start();
                WORKERS.add(worker);
            }
        }


        @Override
        public void execute(Runnable command) {

            QUEUE.add(command);

        }
    }
    static class Worker extends Thread{
        @Override
        public void run() {
            while (true){
                if (!QUEUE.isEmpty()){
                    Runnable task = QUEUE.poll();
                    if (task != null){
                        long start = System.currentTimeMillis();
                        task.run();
                        System.out.println("time elapsed:" + (System.currentTimeMillis() - start));
                    }
                }
            }
        }
    }
}
