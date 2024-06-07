package part01.InterThreadCommunication;

public class ConsumerTask implements Runnable {

    SharedResource sharedResource;

    public ConsumerTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }


    @Override
    public void run() {

        System.out.println("Inside the Consumer Task " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sharedResource.consumeItem();
    }
}
