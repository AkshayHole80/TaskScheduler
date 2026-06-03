import enums.ExecutionResult;
import enums.Priority;
import exception.InvalidPriorityException;
import exception.TaskNotFoundException;
import factory.TaskFactory;
import model.ExecutionRecord;
import model.Task;
import model.TaskType;
import repository.InMemoryTaskRepository;
import retry.FixedRetryPolicy;
import retry.RetryManager;
import service.HistoryService;
import service.TaskExecutor;
import service.TaskScheduler;
import strategy.PrioritySchedulingStrategy;
import strategy.SchedulingStrategy;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        InMemoryTaskRepository repository =
                new InMemoryTaskRepository();

        SchedulingStrategy strategy =
                new PrioritySchedulingStrategy(
                        repository.getTasks()
                );

        TaskScheduler scheduler =
                new TaskScheduler(
                        strategy,
                        repository
                );

        HistoryService historyService =
                new HistoryService();

        TaskExecutor executor =
                new TaskExecutor();

        RetryManager retryManager =
                new RetryManager(
                        new FixedRetryPolicy(3)
                );

        int taskId = 1;

        while (true) {

            System.out.println("\n===== TASK SCHEDULER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Execute Next Task");
            System.out.println("4. View History");
            System.out.println("5. Exit");

            System.out.print("Enter Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Task Name : ");
                    String taskName = scanner.nextLine();

                    System.out.println("Select Priority");
                    System.out.println("1. HIGH");
                    System.out.println("2. MEDIUM");
                    System.out.println("3. LOW");

                    int priorityChoice = scanner.nextInt();
                    scanner.nextLine();

                    Priority priority;

                    switch (priorityChoice) {

                        case 1:
                            priority = Priority.HIGH;
                            break;

                        case 2:
                            priority = Priority.MEDIUM;
                            break;

                        case 3:
                            priority = Priority.LOW;
                            break;

                        default:
                            throw new InvalidPriorityException(
                                    "Invalid Priority Selected"
                            );
                    }

                    Task task =
                            TaskFactory.createTask(
                                    taskId++,
                                    taskName,
                                    priority,
                                    TaskType.EMAIL
                            );

                    scheduler.addTask(task);

                    System.out.println(
                            "Task Added Successfully"
                    );

                    break;

                case 2:

                    scheduler.viewTasks();

                    break;

                case 3:

                    try {

                        Task nextTask =
                                scheduler.getNextTask();

                        boolean success =
                                retryManager.executeWithRetry(
                                        nextTask,
                                        executor
                                );

                        historyService.addRecord(
                                new ExecutionRecord(
                                        nextTask.getName(),
                                        success
                                                ? ExecutionResult.SUCCESS
                                                : ExecutionResult.FAILED,
                                        LocalDateTime.now()
                                )
                        );

                        System.out.println(
                                success
                                        ? "Task Completed Successfully"
                                        : "Task Failed After Retries"
                        );

                    } catch (TaskNotFoundException e) {

                        System.out.println(
                                e.getMessage()
                        );
                    }

                    break;

                case 4:

                    historyService.showHistory();

                    break;

                case 5:

                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:

                    System.out.println(
                            "Invalid Choice"
                    );
            }
        }
    }
}