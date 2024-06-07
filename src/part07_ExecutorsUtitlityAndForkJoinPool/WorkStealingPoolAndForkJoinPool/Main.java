package part07_ExecutorsUtitlityAndForkJoinPool.WorkStealingPoolAndForkJoinPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;



/*
 *
 * When we submit it goes to submission queue
 * When we fork() goes to workStealingQueue, if free it will be taken by thread
 * if thread is free it can steal the task from another thread workStealingQueue
 * */

public class Main {
    public static void main(String[] args) {
        //ForkJoinPool forkJoinPool = new ForkJoinPool();
        //if not given anything
        //Runtime.getRuntime().availableProcessors() that much thread it will create
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        //or we can provide ourselves
        //where min and max both are same
        //ForkJoinPool forkJoinPool = ForkJoinPool.commonPool(4);

        Future<Integer> future = forkJoinPool.submit(new ComputeSumTask(0, 100));

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
