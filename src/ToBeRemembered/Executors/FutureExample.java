package ToBeRemembered.Executors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyRunnable implements Runnable {
    private List<Integer> list;

    public MyRunnable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        // Perform some operation on the list
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) * 2);  // Doubling each element in the list
        }
    }
}

public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Integer> list = List.of(1, 2, 3, 4, 5);  // Immutable List

        // Submitting a task that works on the list and returning the list as the result
        Future<List<Integer>> future = executor.submit(new MyRunnable(list), list);

        // Get the result (which is the same list passed earlier)
        List<Integer> result = future.get();

        System.out.println(result);  // Output: [2, 4, 6, 8, 10]

        executor.shutdown();
    }
}

//Common Variants of submit(): =>

//submit(Runnable task)
//submit(Runnable task, T result)
//submit(Callable<T> task)
