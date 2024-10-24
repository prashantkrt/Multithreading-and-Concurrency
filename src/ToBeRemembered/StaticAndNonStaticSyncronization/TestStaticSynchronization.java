package ToBeRemembered.StaticAndNonStaticSyncronization;

class MyDisplay {
    // Static synchronized method (class-level lock)
    public static synchronized void showMessage(String message) {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread extends Thread {
    MyDisplay display;  // Each thread has a reference to a Display object
    String message;

    MyThread(MyDisplay display, String message) {
        this.display = display;
        this.message = message;
    }

    @Override
    public void run() {
        display.showMessage(message);  // Static synchronized method (class-level lock)
    }
}

public class TestStaticSynchronization {
    public static void main(String[] args) {
        // Creating two Display objects
        MyDisplay display1 = new MyDisplay();
        MyDisplay display2 = new MyDisplay();

        // Creating threads that use different Display objects
        MyThread t1 = new MyThread(display1, "Hello");
        MyThread t2 = new MyThread(display2, "World");

        t1.start();
        t2.start();
    }
}

/*

=> display1 and display2 are two separate objects.
=> Even though the threads (t1 and t2) are accessing the showMessage method via different objects, because the method is static and synchronized, the class-level lock on Display is applied.
=> Only one thread will be able to execute the showMessage method at a time, regardless of how many Display objects exist.

Hello 0
Hello 1
Hello 2
Hello 3
Hello 4
World 0
World 1
World 2
World 3
World 4
* */
