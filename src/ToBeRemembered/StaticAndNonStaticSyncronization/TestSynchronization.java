package ToBeRemembered.StaticAndNonStaticSyncronization;

class Display {
    public synchronized void showMessage(String message) {
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

class MyCustomThread extends Thread {
    Display display;
    String message;

    MyCustomThread(Display display, String message) {
        this.display = display;
        this.message = message;
    }

    @Override
    public void run() {
        display.showMessage(message);  // This will be synchronized per object
    }
}

public class TestSynchronization {
    public static void main(String[] args) {
        Display display1 = new Display();  // Two different objects
        Display display2 = new Display();

        MyCustomThread t1 = new MyCustomThread(display1, "Hello");
        MyCustomThread t2 = new MyCustomThread(display2, "World");

        t1.start();
        t2.start();
    }
}
/*

=> Here, we have two separate Display objects: display1 and display2.
=> Each thread (t1 and t2) calls the showMessage method on a different object.
=> Since each object has its own lock, both threads will execute concurrently, even though the method is synchronized.

Output :->
Hello 0
World 0
Hello 1
World 1
Hello 2
World 2
Hello 3
World 3
Hello 4
World 4

* */
