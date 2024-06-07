package part05_ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),new CustomRejectHandler());

        executor.allowCoreThreadTimeOut(true);//idle thread will get eliminated after keepAliveTime
        for (int i = 1; i <= 7; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Exception occurred");
                }
                System.out.println("Task getting processed by " + Thread.currentThread().getName());
            });
        }
    }
}


