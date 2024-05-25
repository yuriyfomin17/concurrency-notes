package thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.scheduleAtFixedRate(ScheduledExecutor::someBusinessLogic, 1, 1, TimeUnit.SECONDS );
    }

    private static void someBusinessLogic(){
        System.out.println("business logic");
    }
}
