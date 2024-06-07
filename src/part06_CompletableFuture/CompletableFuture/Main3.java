package part06_CompletableFuture.CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//thenCompose and thenComposeAsync

public class Main3 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        //maintains the order even if its async
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("ThreadName->  " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello World";
        }, executor);

        //uses the same Thread
        CompletableFuture<String> completableFuture1 = completableFuture.thenCompose((String val) -> {
            System.out.println("ThreadName->  " + Thread.currentThread().getName());
            List<Integer> list = new ArrayList<>();
            return CompletableFuture.supplyAsync(() -> val + "world");
        });

        //will maintain the same order and also with diff thread
        CompletableFuture<String> completableFuture2 = completableFuture.thenComposeAsync((String val) -> {
            System.out.println("ThreadName 1->  " + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(() -> val + "world");
        }, executor).thenComposeAsync((String val) -> {
            System.out.println("ThreadName 2->  " + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(() -> val + "world");
        }, executor).thenComposeAsync((String val) -> {
            System.out.println("ThreadName 3->  " + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(() -> val + "world");
        }, executor);
    }
}
