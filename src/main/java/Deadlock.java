public class Deadlock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws Exception{

        for (int i = 0; i < 1000_000; i++) {
            Thread thread = new Thread(Deadlock::testMethod);
            Thread thread2 = new Thread(Deadlock::testMethod2);
            thread.start();
            thread2.start();
            thread2.join();
            thread.join();
        }
    }

    private static void testMethod(){
        synchronized (lock1){
            synchronized (lock2){

            }
        }
    }
    private static void testMethod2(){
        synchronized (lock2){
            synchronized (lock1){

            }
        }
    }
}
