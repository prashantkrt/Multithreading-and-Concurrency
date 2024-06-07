package part03_Lock_Based_Mechanism.StampedLock;

import java.util.concurrent.locks.StampedLock;

//optimistic way: by checking the state/version of the lock
public class SharedResourceOptimistic {
    StampedLock lock = new StampedLock();
    private Integer a;

    public void read() {
        long stamp = lock.tryOptimisticRead();//returns the stamp version or state in which it's reading
        try {
            a = 11;
            System.out.println("read lock acquired by producer thread ->: " + Thread.currentThread().getName());
            Thread.sleep(4000);
            if (lock.validate(stamp)) {
                System.out.println("Updated the value successfully ->: " + Thread.currentThread().getName());
            } else {
                a = 0;
                System.out.println("Updated the value failed rollback ... ->: " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write() {
        long stamp = lock.writeLock();//if it writes something, it updates the version
        System.out.println("write lock  acquired by consumer thread ->: " + Thread.currentThread().getName());
        try {
            System.out.println("Performing some task");
            a = 100;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("write lock Unlocked: " + Thread.currentThread().getName());
            lock.unlockWrite(stamp);//unlocks the changed version
        }
    }
}
