package part02.ProducerConsumer;

public class Main {
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource(5);

        Thread produce = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sharedResource.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consume = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sharedResource.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        produce.start();
        consume.start();
    }
}
