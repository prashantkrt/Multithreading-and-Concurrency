package part04_Lock_Free_Mechanism.atomic;

public class SharedResource {

    int counter;

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


    //two solution
    /*
    * 1. make it synchronized
    * 2. using lock-free operation like AtomicInteger
    * */
}
