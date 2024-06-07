package part06_CompletableFuture.CompletableFuture;


import java.util.concurrent.*;

/*
 *thenCombine and thenCombineAsync *
 * used to combine the result of 2 comparable Future
 * */
public class Main5 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        CompletableFuture<Integer> asyncTask1 = CompletableFuture.supplyAsync(() -> {
            return 10;
        }, threadPoolExecutor);

        CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
            return "Hello World";
        }, threadPoolExecutor);


        CompletableFuture<String> combinedFutureObj = asyncTask1.thenCombine(asyncTask2, (a, b) -> a + b);
        try {
            System.out.println(combinedFutureObj.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
