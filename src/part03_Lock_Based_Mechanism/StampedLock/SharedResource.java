package part03_Lock_Based_Mechanism.StampedLock;

import java.util.concurrent.locks.StampedLock;

// it supports the Read/Write lock functionality like ReadWriteLock
public class SharedResource {

    StampedLock lock = new StampedLock();

    private boolean isAvailable = false;

    public void read() {
        //same as the read lock multiple thread can enter
        long stamp = lock.readLock();//returns the stamp version or state in which it's reading
        try {
            System.out.println("read lock acquired by producer thread ->: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Unlocked: " + Thread.currentThread().getName());
            lock.unlockRead(stamp);
        }
    }

    public void write() {
        long stamp = lock.writeLock();//changes the version or state
        try {
            System.out.println("write lock acquired by consumer thread ->: " + Thread.currentThread().getName());
            isAvailable = false;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("write lock Unlocked: " + Thread.currentThread().getName());
            lock.unlockWrite(stamp);//unlocks the changed version
        }
    }
}
