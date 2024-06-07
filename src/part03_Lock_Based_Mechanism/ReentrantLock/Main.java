package part03_Lock_Based_Mechanism.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        //instead of the same object we can pass the same lock
        //it doesn't depend on the different object we can use ReentrantLock for critical section code
        ReentrantLock lock = new ReentrantLock();

        SharedResource resource1 = new SharedResource();
        Thread t1 = new Thread(() -> resource1.producer(lock));
        Thread t2 = new Thread(() -> resource1.producer(lock));

        t1.setName("Resource-1 Thread 1");
        t2.setName("Resource-1 Thread 2");

        SharedResource resource2 = new SharedResource();
        Thread t3 = new Thread(() -> resource2.producer(lock));
        Thread t4 = new Thread(() -> resource2.producer(lock));

        t3.setName("Resource-2 Thread 3");
        t4.setName("Resource-2 Thread 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
