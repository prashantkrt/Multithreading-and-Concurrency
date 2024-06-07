package part01;

public class Main {
    public static void main(String[] args) {
        MonitorLockExample monitorLockExample = new MonitorLockExample();
        Thread thread1 = new Thread(monitorLockExample::task1);
        thread1.start();
        Thread thread2 = new Thread(() -> monitorLockExample.task2());
        thread2.start();
        Thread thread3 = new Thread(monitorLockExample::task3);
        thread3.start();

        // ya toh hum
        // alternate way
        MonitorExampleRunnable runnableObj1 = new MonitorExampleRunnable(monitorLockExample);
        Thread thread4 = new Thread(runnableObj1);

        MonitorExampleRunnable runnableObj2 = new MonitorExampleRunnable(monitorLockExample);
        Thread thread5 = new Thread(runnableObj2);

        MonitorExampleRunnable runnableObj3 = new MonitorExampleRunnable(monitorLockExample);
        Thread thread6 = new Thread(runnableObj3);
    }
}
