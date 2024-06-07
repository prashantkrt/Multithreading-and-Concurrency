package part03_Lock_Based_Mechanism.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// producer consumer problem with the locks
public class SharedResource {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private boolean isAvailable = false;

    public void produce() {
        try {
            lock.lock();
            System.out.println("Producer lock acquired by : " + Thread.currentThread().getName());
            if (isAvailable) {
                //already available, thread has to wait for it to continue
                System.out.println("Producer thread is waiting :" + Thread.currentThread().getName());
                condition.await();// waiting with respect to the lock
            } else {
                isAvailable = true;
                condition.signal();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Producer thread exits : " + Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void consume() {
        try {
            lock.lock();
            System.out.println("Consumer lock acquired by : " + Thread.currentThread().getName());
            if (!isAvailable) {
                System.out.println("Consumer thread is waiting :" + Thread.currentThread().getName());
                condition.await();
            } else {
                isAvailable = false;
                condition.signal();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Consumer thread exits : " + Thread.currentThread().getName());
            lock.unlock();
        }
    }

}
