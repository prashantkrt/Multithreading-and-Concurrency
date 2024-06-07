package part03_Lock_Based_Mechanism.Condition;

public class Main {
    public static void main(String[] args) {
        SharedResource resource1 = new SharedResource();

        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                resource1.produce();
            }
        });

        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                resource1.consume();
            }
        });

        producer1.start();
        consumer1.start();
    }
}
