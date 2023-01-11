package PartB;

import java.util.concurrent.*;

public class CustomExecutor <T> extends ThreadPoolExecutor {

    private int maxPriority;
    private int[] priorities = new int[11];

    /**
     * Constructor
     * we sent to the super constructor the demands for our ThreadPollExecutor
     */
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300L, TimeUnit.MILLISECONDS,new PriorityBlockingQueue<Runnable>());
    }


    /**
     * ---section 1---
     *Submit method, creates a FutureTask with priority - PartB.MyFutureTask and executes
     * in addition the function adds to the array of priorities 1 task at the right place
     * @param task - PartB.Task object - callable type
     * @param <V> - PartB.Task is generic
     * @return our RunnableFuture - PartB.MyFutureTask
     */
    public <V> Future <V> submit( Task <V> task) {
        MyFutureTask futureTask = new MyFutureTask(task.getTask(), task.getTaskType().getPriorityValue());
        this.priorities[task.getTaskType().getPriorityValue()]++;
        execute(futureTask);
        return futureTask;
    }


    /**
     * ---section 2---
     * submit method , creates PartB.Task and sends it to the submit function above
     * @param task - callable type of task
     * @param taskType - enum PartB.TaskType - the name and priority of the task of the task
     * @param <V> - Callable is generic
     * @return the PartB.MyFutureTask the submit function of section 1 returns
     */
    public <V> Future <V> submit(Callable<V> task, TaskType taskType) {
        return submit(Task.createTask( task, taskType));
    }

    /**
     * ---section 3---
     * submit method , creates PartB.Task with default priority and sends it to the submit function above
     * @param task - callable type of task
     * @param <V> - Callable is generic
     * @return the PartB.MyFutureTask the submit function of section 1 returns
     */
    public <V> Future <V> submit(Callable<V> task) {
        return submit( Task.createTask(task));
    }

    /**
     * this function captures the PartB.Task right before its executes and removes its priority
     * from the priorities array and saving the next in line priority -
     * the max priority - in this case is 1
     * @param t - the thread that will run the Runnable task
     * @param r - the task that will run next
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        this.priorities[((MyFutureTask<T>) r).getPriority()]--;
        for (int i = 1; i < this.priorities.length; i++) {
            if (this.priorities[i] != 0) {
                this.maxPriority = i;
                break;
            }
        }
    }

    /**
     * ---section 10---
     * this function returns the next PartB.Task in line using thr beforeExecute function
     * this function is O(1) because the loop is in the worst case goes throw all
     * the arrays which is constant number - 10
     * @return the max priority in the queue
     */
    public int getCurrentMax() {
        return this.maxPriority;

    }


    /**
     * ---section 11---
     * this function wait for all the tasks in process and those in the queue
     * it does not allow new tasks to be executed
     * when the await function return true the thread-pool is shut down
     */
    public void gracefullyTerminate() {
        try {
            while((awaitTermination(10,TimeUnit.MILLISECONDS)))
                     shutdown();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

    }
}


