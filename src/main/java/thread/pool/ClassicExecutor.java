package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClassicExecutor {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(ClassicExecutor::businessLogic);
        executor.execute(ClassicExecutor::businessLogic);
        executor.execute(ClassicExecutor::businessLogic);
        executor.execute(ClassicExecutor::businessLogic);
        executor.execute(ClassicExecutor::businessLogic);
        executor.execute(ClassicExecutor::businessLogic);


        // shutdown is to wait till business logic will be executed then shutdown
//        executor.shutdownNow();
        executor.shutdown();
    }

    private static void businessLogic() {
        try {
            Thread.sleep(5000);
            System.out.println("hello from thread" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
