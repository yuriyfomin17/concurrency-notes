public class InfiniteNumberOfThreads {

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            new Thread(InfiniteNumberOfThreads::businessLogic).start();
        }
    }
    private static void businessLogic(){
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
