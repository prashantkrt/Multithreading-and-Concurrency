package part01.InterThreadCommunication;

public class ProducerTask implements Runnable {

    SharedResource sharedResource;

    ProducerTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }


    @Override
    public void run() {

        System.out.println("Inside the producer task " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sharedResource.addItem();
    }
}
