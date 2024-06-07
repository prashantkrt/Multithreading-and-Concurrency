package part07_ExecutorsUtitlityAndForkJoinPool.ExecutorsUtitlity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //method creates a thread pool executor with a fixed no. of threads
        /*
         * min and max are the same
         * queue size unbounded queue
         * thread alive when idle: yes, as there is not keepAliveTime provided, so it will be alive even if idle
         *
         * when to use: - exact info, how amy Async task is needed like I need 5 thread
         * disadvantage: - not good for heavy workload as it leads to limited concurrency
         *
         * */

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> "this is the async task");

        /*
         *
         * newCachedThreadPool method creates a thread pool that creates a new thread as need dynamically
         *
         * min and max: min 0 and max->Integer.MAX_VALUE
         * Queue Size: Blocking Queue with size 0
         * Thread Alive when Idle: 60 secs
         *
         * when to use: Good for handling burst of short-lived tasks,
         *
         * It's not suggested for long-lived task
         * disadvantage :->Many long-lived tasks and submitted rapidly, ThreadPool can create so many
         * threads which might lead to increasing memory usage
         * */

        ExecutorService executorService2 = Executors.newCachedThreadPool();
        executorService2.submit(() -> "this is the cached task");

        /*
        * newSingleThreadPool()
        * creates Executor with just a single worker thread.
        *
        * Min and Mix: 1 : 1
        * Queue Size Unblocking Queue
        * Thread Alive when idle: yes
        *
        * When to use: When wanted to process a task sequentially
        * disadvantage: No Concurrency at all
        * */


        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        executorService3.submit(() -> "this is the single thread task");


    }
}
