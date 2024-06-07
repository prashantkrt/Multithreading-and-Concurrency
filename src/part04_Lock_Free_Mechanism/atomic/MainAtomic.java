package part04_Lock_Free_Mechanism.atomic;

public class MainAtomic {
    public static void main(String[] args) throws InterruptedException {

        SharedResourceAtomic resource = new SharedResourceAtomic();

        var t1 = new Thread(() -> {
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

        System.out.println(resource.get());
    }
}
