package part04_Lock_Free_Mechanism.Volatile;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        SharedResource resource = new SharedResource();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(resource.getCounter());
    }
}
