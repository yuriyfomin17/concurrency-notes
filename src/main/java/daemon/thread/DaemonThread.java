package daemon.thread;

public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // if set to true then program will exit without waiting to this thread to finish
        thread.setDaemon(false);
        thread.start();
    }
}
