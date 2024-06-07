package part02;

public class JoinExample {

    private final int data;
    public JoinExample(int data) {
        this.data = data;
    }

    public synchronized void data() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("data: " + data);
    }
}
