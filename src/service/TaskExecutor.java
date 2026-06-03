package service;

import enums.TaskStatus;
import model.Task;

import java.util.Random;
import java.util.logging.Logger;

public class TaskExecutor {

    private static final Logger logger =
            Logger.getLogger(TaskExecutor.class.getName());

    private final Random random =
            new Random();

    public boolean execute(Task task) {

        task.setStatus(
                TaskStatus.RUNNING
        );

        logger.info(
                "Executing task : " +
                        task.getName()
        );

        boolean success =
                random.nextInt(100) < 70;

        if(success) {

            task.setStatus(
                    TaskStatus.COMPLETED
            );

            logger.info(
                    "Task completed : " +
                            task.getName()
            );

            return true;
        }

        task.setStatus(
                TaskStatus.FAILED
        );

        logger.warning(
                "Task failed : " +
                        task.getName()
        );

        return false;
    }
}