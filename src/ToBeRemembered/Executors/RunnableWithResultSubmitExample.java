package ToBeRemembered.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//submit(Runnable task, T result)
public class RunnableWithResultSubmitExample {
    public static void main(String[] args) {
        // Create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable task (does not return any value)
        Runnable task = () -> {
            System.out.println("Runnable task with result is running.");
        };

        // Submit the task with a result to return
        Future<String> future = executor.submit(task, "Task Completed");

        // Get the result after task completion
        try {
            String result = future.get();  // Returns "Task Completed"
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
