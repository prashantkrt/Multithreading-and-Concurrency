package part03_Lock_Based_Mechanism.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
// SharedLock -> readLock
// ExclusiveLock -> writeLock

/*
* if it has sharedLock then no exclusive lock can be given
* if there is no shared lock then only exclusive lock can be taken
*
* use: - when read are very high as compared to write
* */
public class SharedResource {

    boolean isAvailable = false;

    public void read(ReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("read lock acquired by producer thread ->: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Unlocked: " + Thread.currentThread().getName());
            lock.readLock().unlock();
        }
    }

    public void write(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("write lock  acquired by consumer thread ->: " + Thread.currentThread().getName());
            isAvailable = false;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("write lock Unlocked: " + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }

}
