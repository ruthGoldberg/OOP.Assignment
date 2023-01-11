import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.Comparator;

public class MyFutureTask<T> extends FutureTask implements Comparable <MyFutureTask<T>> {
    private int priority;

    public MyFutureTask(Callable callable , int p ) {
        super(callable);
        this.priority = p;
    }


    public int getPriority() {
        return priority;
    }


    @Override
    public int compareTo(MyFutureTask<T> o) {
        if(this.priority > o.priority )
            return -1;
        if(this.priority < o.priority)
            return 1;
        return 0;
    }

}
