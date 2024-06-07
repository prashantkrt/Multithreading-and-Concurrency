package part04_Lock_Free_Mechanism.Volatile;

public class SharedResource {

    // means read and write should be directly done from the memory not from the l1 cache or register
    // any thread done by the one thread is visible to the other thread also from memory rather than individual l1 cache or register
    // but this doesn't mean that it provides the thread safety
    private volatile int counter;

    public void increment() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter++;
    }

    public int getCounter() {
        return counter;
    }

}
