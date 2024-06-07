package part01.InterThreadCommunication;

public class SharedResource {

    private boolean itemAvailable = false;

    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println("Item added " + Thread.currentThread().getName());
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("Consuming " + Thread.currentThread().getName());

        // if item not available keep the thread into the wait state
        // we are using while here because as per oracle docs , "spurious wake-up" can happen so using while loop to avoid it
        while (!itemAvailable) {
            try {
                System.out.println("Consume method is invoked " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Item consumed " + Thread.currentThread().getName());
        itemAvailable = false;
    }
}
