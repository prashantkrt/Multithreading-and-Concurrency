package part03_Lock_Based_Mechanism.SemaphoreLock;

import java.util.concurrent.Semaphore;

public class SharedResource {

    boolean isAvailable = false;
    Semaphore lock = new Semaphore(2);
    // It allows the particular thread to go inside the critical section
    // It has the feature which we can decide how many threads can enter the thread at the same time

    public void producer() {
        try {
            lock.acquire();
            System.out.println("Lock acquired by : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Lock released by : " + Thread.currentThread().getName());
            lock.release();
        }
    }


}
