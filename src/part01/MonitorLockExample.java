package part01;

public class MonitorLockExample {

    public synchronized void task1() {
        System.out.println("inside the task1!!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void task2() {
        System.out.println("inside the task2!!");

        synchronized (this) {
            System.out.println("inside the synchronized block of task2!!");
        }
    }

    public void task3() {
        System.out.println("inside the task3!!");
    }
}
