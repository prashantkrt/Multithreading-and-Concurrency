package part06_CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * Callable is a Functional Interface that helps to return
 * */
public class MainCallable {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Future<List<Integer>> future = executor.submit(() -> {
            //do some task
            List<Integer> output = new ArrayList<>();
            output.add(300);
            return output;

        });

        Future<List<Integer>> futureObj = executor.submit(new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                return List.of();
            }
        });
    }
}
