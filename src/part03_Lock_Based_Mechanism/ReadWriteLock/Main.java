package part03_Lock_Based_Mechanism.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {
        SharedResource resource1 = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread thread1 = new Thread(() -> resource1.read(lock));
        Thread thread2 = new Thread(() -> resource1.write(lock));

        SharedResource resource2 = new SharedResource();
        Thread thread3 = new Thread(() -> resource2.read(lock));
        Thread thread4 = new Thread(() -> resource2.write(lock));

        //Diff thread can acquire multiple read lock at the same time
        Thread t1 = new Thread(() -> resource1.read(lock));
        Thread t2 = new Thread(() -> resource1.read(lock));
        Thread t3 = new Thread(() -> resource1.read(lock));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        t1.start();
        t2.start();
        t3.start();

    }
}
