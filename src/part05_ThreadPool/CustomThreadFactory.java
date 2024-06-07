package part05_ThreadPool;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(false);//by default
        thread.setName("my demon thread");
        thread.setPriority(Thread.MIN_PRIORITY);
        return thread;
    }
}
