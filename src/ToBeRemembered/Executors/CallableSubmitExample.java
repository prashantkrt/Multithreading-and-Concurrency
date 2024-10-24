package ToBeRemembered.Executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//submit(Callable<T> task)
public class CallableSubmitExample {
    public static void main(String[] args) {
        // Create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Callable task (returns a result)
        Callable<Integer> task = () -> {
            System.out.println("Callable task is running.");
            return 42;  // Return some result
        };

        // Submit the Callable task for execution
        Future<Integer> future = executor.submit(task);

        // Get the result from the task
        try {
            Integer result = future.get();  // Returns 42
            System.out.println("Result from future: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Shutdown the executor
        executor.shutdown();
    }
}

// Output : ⇒
// Runnable task with result is running.
// Result from future: Task Completed

//Common Variants of submit(): =>

//submit(Runnable task)
//submit(Runnable task, T result)
//submit(Callable<T> task)


/*
submit(Runnable task):
Used for tasks that don’t return a result.
Future.get() will return null.

submit(Runnable task, T result):
Used for tasks that don’t return a result but you want to provide a predefined result.
Future.get() returns the provided result.


submit(Callable<T> task):
Used for tasks that return a result.
Future.get() returns the result produced by the Callable.
*/
