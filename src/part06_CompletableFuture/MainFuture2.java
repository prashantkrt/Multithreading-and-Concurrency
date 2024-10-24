package part06_CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainFuture2 {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executor.allowCoreThreadTimeOut(true);
        List<Integer> list = new ArrayList<>();
        // Submitting a task that works on the list and returning the list as the result
        Future<List<Integer>> future = executor.submit(new MyRunnable(list), list);

        try {
            //1st way
            List<Integer> output = future.get();
            System.out.println(output.get(0));

            //2nd way
            System.out.println(list.get(0));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}


//Common Variants of submit(): =>

//submit(Runnable task)
//submit(Runnable task, T result)
//submit(Callable<T> task)
