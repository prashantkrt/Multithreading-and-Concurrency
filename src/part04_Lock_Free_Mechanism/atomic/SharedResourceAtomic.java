package part04_Lock_Free_Mechanism.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResourceAtomic {

    //read, modify and update only
    AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }
}
