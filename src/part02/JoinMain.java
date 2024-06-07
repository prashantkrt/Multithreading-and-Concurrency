package part02;

//Join method is invoked on a thread object. Current thread will be blocked and waits for the specific thread to finish
public class JoinMain implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Inside JoinMain " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main Thread Started " + Thread.currentThread().getName());
        Thread thread = new Thread(new JoinMain());
        Thread thread1 = new Thread(new JoinMain());
        Thread thread2 = new Thread(new JoinMain());
        thread.start();
        thread1.start();
        thread2.start();
        thread.join();
        thread1.join();
        thread2.join();

        JoinExample joinExample = new JoinExample(12);
        Thread thread3 = new Thread(joinExample::data);
        thread3.start();
        System.out.println("Main Thread Ended " + Thread.currentThread().getName());
    }
}
