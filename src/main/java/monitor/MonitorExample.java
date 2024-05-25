package monitor;

public class MonitorExample {
    private static  int counter = 0;

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> businessLogic());
        Thread thread1 = new Thread(() -> businessLogic());
        Thread thread2 = new Thread(() -> businessLogic());
        Thread thread3 = new Thread(() -> businessLogic());
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread.join();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(counter);
    }

    private static synchronized void businessLogic(){
        System.out.println("hello from thread:" + Thread.currentThread().getName());
        try {
            counter += 1;
//            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}