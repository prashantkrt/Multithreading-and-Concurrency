package part07_ExecutorsUtitlityAndForkJoinPool.WorkStealingPoolAndForkJoinPool;

import java.util.concurrent.RecursiveTask;

public class ComputeSumTask extends RecursiveTask<Integer> {

    int start;
    int end;

    ComputeSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            ComputeSumTask left = new ComputeSumTask(start, middle);
            ComputeSumTask right = new ComputeSumTask(middle + 1, end);

            // fork the subtask for parallel execution
            // thread will take one part
            //another part will be in workStealingQueue of that thread
            //we are splitting the task in two parts
            left.fork();
            right.fork();

            //combining the result and returning it
            return left.join() + right.join();
        }
    }
}
