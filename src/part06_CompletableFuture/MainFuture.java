package part06_CompletableFuture;

import java.util.concurrent.*;

public class MainFuture {
    public static void main(String[] args) {
        // know the status from Future,
        // It is an interface that is very helpful if we wanted to know if the task is completed or has any exception or cancelled
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executor.allowCoreThreadTimeOut(true);

        Future<?> future = executor.submit(() -> {
            System.out.println("This is the task which will execute");
        });

        System.out.println("is done " + future.isDone());

        try {
            future.get(2, TimeUnit.SECONDS);// will wait and post-time completion returns Exception if not completed within the time
            // else returns the timeout exception if not completed within the time limit
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        try {
            future.get();// once completed, then returns
            //this is a waiting period until a task gets completed
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("is done again " + future.isDone());
        System.out.println("is cancelled " + future.isCancelled());

        //future.cancel(true);//cancels the task

    }
}
