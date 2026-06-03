package retry;

import model.Task;
import service.TaskExecutor;

import java.util.logging.Logger;

public class RetryManager {

    private static final Logger logger =
            Logger.getLogger(
                    RetryManager.class.getName()
            );

    private final RetryPolicy retryPolicy;

    public RetryManager(
            RetryPolicy retryPolicy) {

        this.retryPolicy = retryPolicy;
    }

    public boolean executeWithRetry(
            Task task,
            TaskExecutor executor) {

        while (
                retryPolicy.shouldRetry(task)
        ) {

            boolean success =
                    executor.execute(task);

            if(success) {

                logger.info(
                        "Task succeeded : "
                                + task.getName()
                );

                return true;
            }

            task.incrementRetryCount();

            logger.warning(
                    "Retry Count : "
                            + task.getRetryCount()
            );
        }

        return false;
    }
}