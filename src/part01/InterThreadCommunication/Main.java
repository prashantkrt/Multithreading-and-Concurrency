package part01.InterThreadCommunication;

public class Main {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        ProducerTask producerTask = new ProducerTask(sharedResource);
        ConsumerTask consumerTask = new ConsumerTask(sharedResource);
        Thread producer = new Thread(producerTask);
        Thread consumer = new Thread(consumerTask);
        producer.start();
        consumer.start();


        //better approach
        Thread producer2 = new Thread(() -> sharedResource.addItem());
        Thread consumer2 = new Thread(() -> sharedResource.consumeItem());
        producer2.start();
        consumer2.start();

        Thread producer3 = new Thread(new Runnable() {
            @Override
            public void run() {
                sharedResource.addItem();
            }
        });
        Thread consumer3 = new Thread(new Runnable() {
            @Override
            public void run() {
                sharedResource.consumeItem();
            }
        });
        producer3.start();
        consumer3.start();

    }
}
