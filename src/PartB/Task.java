package PartB;

import java.util.concurrent.Callable;

public class Task <T> implements Callable<T> {

    private TaskType taskType;
    private Callable task;


    /**
     * private constructor , build new task throw createTask function
     * @param task - callable type of task
     * @param taskType - contains the type and priority of the task
     */
    private Task( Callable task,TaskType taskType) {
        this.taskType = taskType;
        this.task = task;

    }

    /**
     * private default constructor , build new task throw createTask function
     * the default of a task without PartB.TaskType is th IO PartB.TaskType
     * @param task - callable type of task
     */
    private Task( Callable task) {
        this.taskType = TaskType.IO;
        this.task = task;
    }

    /**
     * the real constructor of the PartB.Task class , is public and send the PartB.Task parameters to the private constructor
     * @param task - callable type of task
     * @param taskType -  enum PartB.TaskType - the name and priority of the task of the task
     * @param <T> - PartB.Task is generic
     * @return the new PartB.Task
     */
    public static <T>Task createTask(Callable task, TaskType taskType) {
       return (new Task(task , taskType));
    }


    /**
     * the real constructor of the PartB.Task class , is public and send the PartB.Task parameters to the private constructor
     * @param task - callable type of task
     * @param <T>- PartB.Task is generic
     * @return the new PartB.Task
     */
    public static <T>Task createTask(Callable task) {
        return (new Task(task));
    }


    /**
     * compare two tasks if they equal - same PartB.TaskType same callable
     * @param task - the other task to compare with the current task
     * @return true if equal false if not
     */

    public boolean equals(Task task) {
        if(this.taskType != task.taskType)
            return false;
        if(this.task != task.task)
            return false;
        return true;
    }

    /**
     * this method contains the callable task
     * @return the generic result of the task
     * @throws Exception
     */
    @Override
    public T call() throws Exception {
        return (T) this.task;
    }


    /**
     * get function to the PartB.TaskType
     * @return the Tasks' PartB.TaskType
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * get function to the PartB.Task
     * @return the Callable task
     */
    public Callable getTask() {
        return task;
    }

}



