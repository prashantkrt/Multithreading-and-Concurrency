package part06_CompletableFuture.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * thenAccept and  thenAcceptAsync()
 * Generally end stage, in the chain of Async operations
 * It doesn't return anything.
 * */
public class Main4 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        //uses the same thread
        CompletableFuture<Void> asyncTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("ThreadName = " + Thread.currentThread().getName());
            return "Hello World";
        }, threadPoolExecutor).thenAccept((String val) -> {
            System.out.println("ThreadName = " + Thread.currentThread().getName());
            System.out.println(val);
        });

        //Multiple thread can be used
        CompletableFuture<Void> asyncTask2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("ThreadName = " + Thread.currentThread().getName());
            return "Hello World";
        }, threadPoolExecutor).thenAcceptAsync((String val) -> {
            System.out.println("ThreadName = " + Thread.currentThread().getName());
            System.out.println(val);
        }, threadPoolExecutor);
    }
}
