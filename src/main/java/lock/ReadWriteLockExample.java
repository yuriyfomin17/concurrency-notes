package lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static int counter = 0;

    public static void main(String[] args) {
        new Thread(ReadWriteLockExample::read).start();
        new Thread(ReadWriteLockExample::read).start();
        new Thread(ReadWriteLockExample::increment).start();
        new Thread(ReadWriteLockExample::read).start();

    }

    private static void read() {
        READ_WRITE_LOCK.readLock().lock();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Reading counter:" + counter + " by thread:" + Thread.currentThread().getName());
        READ_WRITE_LOCK.readLock().unlock();

    }

    private static void increment() {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter += 1;
        System.out.println("Writing counter:" + counter + " by thread:" + Thread.currentThread().getName());
        READ_WRITE_LOCK.writeLock().unlock();

    }

}