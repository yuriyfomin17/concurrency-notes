import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

public class CustomThreadPoolExecutorExample {

    private static final LinkedList<Thread> workers = new LinkedList<>();
    private static final Queue<Runnable> runnableQueue = new ArrayDeque<>();

    public static void main(String[] args) {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(5);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
        executor.execute(CustomThreadPoolExecutorExample::businessLogic);
    }
    private static void businessLogic(){
        System.out.println("Hello from thread:" + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class CustomThreadPoolExecutor implements Executor {

        public CustomThreadPoolExecutor(int numThreads){
            for (int i = 0; i < numThreads; i++) {
                Thread worker = new Worker();
                worker.start();
                workers.add(worker);
            }
        }
        @Override
        public void execute(Runnable command) {
            synchronized (runnableQueue) {
                runnableQueue.add(command);
                runnableQueue.notify();
            }
        }
    }


    private static class Worker extends Thread {
        @Override
        public void run() {
            synchronized (runnableQueue) {
                while (true) {
                    if (runnableQueue.isEmpty()) {
                        try {
                            runnableQueue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Runnable task = runnableQueue.poll();
                    task.run();
                }
            }
        }
    }
}
