package ToBeRemembered.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//submit(Runnable task)
public class RunnableSubmitExample {
    public static void main(String[] args) {

        // Create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable task (does not return any value)
        Runnable task = () -> {
            System.out.println("Runnable task is running.");
        };

        // Submit the task for execution
        Future<?> future = executor.submit(task);

        // Check if the task is done
        try {
            future.get();  // Will return null, as Runnable does not return anything
            System.out.println("Task is completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
// Output : =>

// Runnable task is running.
// Task is completed.

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
