package part06_CompletableFuture.CompletableFuture;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main2 {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("ThreadName->  " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello World";
        }, threadPoolExecutor);

        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply((String str) -> {
            System.out.println("ThreadName  " + Thread.currentThread().getName());
            return str.toUpperCase() + "in upper case";
        });

        //uses different thread and may change the pattern of execution
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("ThreadName : main " + Thread.currentThread().getName());
            return "myWorld";
        }, threadPoolExecutor).thenApplyAsync((String val) -> {
            System.out.println("ThreadName : upper  " + Thread.currentThread().getName());
            return val.toUpperCase() + "in upper case";
        }).thenApplyAsync((String val) -> {
            System.out.println("ThreadName : lower  " + Thread.currentThread().getName());
            return val.toLowerCase() + "in lower case";
        });
    }
}
