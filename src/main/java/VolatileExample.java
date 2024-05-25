public class VolatileExample {
    private static volatile boolean flag = true;

    public static void main(String[] args) throws Exception{

        Thread threadOneFinished = new Thread(() -> {
            while (flag) {

            }
            System.out.println("thread one finished");
        });

        Thread threadTwoFinished = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = false;
            System.out.println("thread two finished");
        });

        threadOneFinished.start();
        threadTwoFinished.start();
        threadOneFinished.join();
        threadTwoFinished.join();


    }
}

