package retry;

import model.Task;

public interface RetryPolicy {

    boolean shouldRetry(Task task);

}