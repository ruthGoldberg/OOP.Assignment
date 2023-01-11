package PartB;

import java.util.Comparator;

public class TaskComparator implements Comparator <Task> {

    @Override
    public int compare(Task o1, Task o2) {
       return Integer.compare(o1.getTaskType().getPriorityValue() , o2.getTaskType().getPriorityValue());

    }
}
