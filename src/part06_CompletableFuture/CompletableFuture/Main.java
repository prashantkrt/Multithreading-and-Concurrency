package part06_CompletableFuture.CompletableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10)
                , Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);
        // if not provided any pool, then it will by default take a Fork-Join pool executor that dynamically adjusts its size
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "task completed";
        }, threadPoolExecutor);

        try {
            System.out.println(stringCompletableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // by default fork join pool used here
        CompletableFuture<List<Integer>> completableFuture = CompletableFuture.supplyAsync(new Supplier<List<Integer>>() {

            @Override
            public List<Integer> get() {
                return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
            }
        });

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<List<Integer>> completablefuture = CompletableFuture.supplyAsync(() -> {
                return Arrays.asList(1, 2, 3, 4);
            });
            try {
                list.add(completablefuture.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        //thenApply and thenApplySync -> chaining

        /*
         * thenApply is a synchronous execution
         * it uses the same thread which is completed the previous Async task *
         * */
        CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }, threadPoolExecutor).thenApply((String val) -> {
            return val + "world";
        });

        try {
            System.out.println(asyncTask1.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        /*
         * thenApplySync
         * It is an Asynchronous execution
         * Means, it uses the different thread
         * if Multiple-thenApplyAsync is used then ordering cannot be guaranteed,
         * they all will run concurrently
         *
         * */

        CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }, threadPoolExecutor).thenApplyAsync((String val) -> {
            return val + "world";
        });

        try {
            System.out.println(asyncTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
