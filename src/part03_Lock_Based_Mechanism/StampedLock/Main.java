package part03_Lock_Based_Mechanism.StampedLock;


public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(resource::read);
        Thread t2 = new Thread(resource::write);
        Thread t3 = new Thread(resource::read);
        Thread t4 = new Thread(resource::write);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
