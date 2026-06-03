package service;

import enums.ExecutionResult;
import enums.TaskStatus;
import exception.TaskExecutionException;
import model.ExecutionRecord;
import model.Task;
import retry.RetryPolicy;

import java.time.LocalDateTime;
import java.util.Random;

public class TaskExecutor {

    private final RetryPolicy retryPolicy;

    private final HistoryService
            historyService;

    private final Random random =
            new Random();

    public TaskExecutor(
            RetryPolicy retryPolicy,
            HistoryService historyService) {

        this.retryPolicy = retryPolicy;
        this.historyService =
                historyService;
    }

    public void execute(Task task) {

        while (true) {

            try {

                task.setStatus(
                        TaskStatus.RUNNING);

                System.out.println(
                        "\nExecuting : "
                                + task.getName());

                boolean success =
                        random.nextInt(100)
                                < 70;

                if(!success) {

                    throw new TaskExecutionException(
                            "Execution failed"
                    );
                }

                task.setStatus(
                        TaskStatus.COMPLETED);

                historyService.addRecord(
                        new ExecutionRecord(
                                task.getName(),
                                ExecutionResult.SUCCESS,
                                LocalDateTime.now()
                        )
                );

                System.out.println(
                        "Completed : "
                                + task.getName());

                return;

            }
            catch (TaskExecutionException e) {

                task.incrementRetryCount();

                if(retryPolicy
                        .shouldRetry(task)) {

                    System.out.println(
                            "Retrying Task : "
                                    + task.getName()
                                    + " Retry Count : "
                                    + task.getRetryCount());

                }
                else {

                    task.setStatus(
                            TaskStatus.FAILED);

                    historyService.addRecord(
                            new ExecutionRecord(
                                    task.getName(),
                                    ExecutionResult.FAILED,
                                    LocalDateTime.now()
                            )
                    );

                    System.out.println(
                            "Task Failed : "
                                    + task.getName());

                    return;
                }
            }
        }
    }
}