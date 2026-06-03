package retry;

import model.Task;

public class FixedRetryPolicy
        implements RetryPolicy {

    private final int maxRetries;

    public FixedRetryPolicy(
            int maxRetries) {

        this.maxRetries = maxRetries;
    }

    @Override
    public boolean shouldRetry(Task task) {

        return task.getRetryCount()
                < maxRetries;
    }
}