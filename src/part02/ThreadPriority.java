package part02;

public class ThreadPriority implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Inside the thread with the given priority "+Thread.currentThread().getName()+" "+Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadPriority());
        Thread t2 = new Thread(new ThreadPriority());
        Thread t3 = new Thread(new ThreadPriority());
        Thread t4 = new Thread(new ThreadPriority());
        Thread t5 = new Thread(new ThreadPriority());
        Thread t6 = new Thread(new ThreadPriority());

        /*
        * Priority ranges from 1-10
        * Lower priority: 1
        * High priority: 10
        *
        * also, we can set Thread.MAX_PRIORITY & Thread.MIN_PRIORITY
        *
        * higher the value, higher the priority
        */

        //avoid using the thread priority overall


        //Even if we set the thread priority, it doesn't guarantee to follow the specific order
        // It's just a hint to thread scheduler which to execute the next


        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);//default to every thread value 5
        t2.setPriority(3);
        t6.setPriority(4);
        t5.setPriority(9);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        System.out.println(t1.getName()+" "+t1.getPriority());
        System.out.println(t2.getName()+" "+t2.getPriority());
        System.out.println(t3.getName()+" "+t3.getPriority());
        System.out.println(t4.getName()+" "+t4.getPriority());
        System.out.println(t5.getName()+" "+t5.getPriority());
    }
}
