package part02;

//something running async
// low-priority thread that runs in the background to perform tasks such as garbage collection or
//provide services to another thread
public class DaemonThread implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) {
        System.out.println("Main thread is running "+Thread.currentThread().getName());
        // Demon thread is alive if any one of the user threads is alive
        // it's life depends on the user thread
        // if all user thread completed then demon also gets completed
        Thread t = new Thread(new DaemonThread());
        t.setDaemon(true);// if true, then Demon thread else user thread

        Thread t2 = new Thread(new DaemonThread());
        t2.setName("User Thread");
        t2.start();//user thread
        System.out.println("Main thread is ended "+Thread.currentThread().getName());
    }
}
