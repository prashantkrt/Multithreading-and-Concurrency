package part02.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private final Queue<Integer> sharedBuffer;
    private final int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Waiting for shared buffer to be empty");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Produced " + item);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (sharedBuffer.isEmpty()) {
            System.out.println("Waiting for shared buffer to have some data to be consumed");
            wait();
        }
        int item = sharedBuffer.poll();
        System.out.println("Consumed " + item);
        notify();
    }
}
