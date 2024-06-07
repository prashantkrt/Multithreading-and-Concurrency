package part01;

public class MonitorExampleRunnable implements Runnable{

    MonitorLockExample obj;

    MonitorExampleRunnable(MonitorLockExample obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        obj.task1();
    }
}
