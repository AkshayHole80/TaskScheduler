import enums.Priority;
import exception.InvalidPriorityException;
import exception.TaskNotFoundException;
import model.Task;
import retry.FixedRetryPolicy;
import retry.RetryPolicy;
import service.HistoryService;
import service.TaskExecutor;
import service.TaskScheduler;
import strategy.PrioritySchedulingStrategy;
import strategy.SchedulingStrategy;
import utils.TaskPriorityComparator;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        PriorityQueue<Task> taskQueue =
                new PriorityQueue<>(
                        new TaskPriorityComparator()
                );

        SchedulingStrategy strategy =
                new PrioritySchedulingStrategy(
                        taskQueue
                );

        RetryPolicy retryPolicy =
                new FixedRetryPolicy(3);

        HistoryService historyService =
                new HistoryService();

        TaskScheduler scheduler =
                new TaskScheduler(
                        strategy,
                        taskQueue
                );

        TaskExecutor executor =
                new TaskExecutor(
                        retryPolicy,
                        historyService
                );

        int taskId = 1;

        while (true) {

            System.out.println(
                    "\n===== TASK SCHEDULER =====");

            System.out.println(
                    "1. Add Task");

            System.out.println(
                    "2. View Tasks");

            System.out.println(
                    "3. Execute Next Task");

            System.out.println(
                    "4. View History");

            System.out.println(
                    "5. Exit");

            System.out.print(
                    "Enter Choice : ");

            int choice =
                    scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Task Name : ");

                    String taskName =
                            scanner.nextLine();

                    System.out.println(
                            "1. HIGH");

                    System.out.println(
                            "2. MEDIUM");

                    System.out.println(
                            "3. LOW");

                    int priorityChoice =
                            scanner.nextInt();

                    Priority priority;

                    switch (priorityChoice) {

                        case 1:
                            priority =
                                    Priority.HIGH;
                            break;

                        case 2:
                            priority =
                                    Priority.MEDIUM;
                            break;

                        case 3:
                            priority =
                                    Priority.LOW;
                            break;

                        default:
                            throw new InvalidPriorityException(
                                    "Invalid Priority"
                            );
                    }

                    scheduler.addTask(
                            new Task(
                                    taskId++,
                                    taskName,
                                    priority
                            )
                    );

                    break;

                case 2:

                    scheduler.viewTasks();

                    break;

                case 3:

                    try {

                        Task task =
                                scheduler.getNextTask();

                        executor.execute(task);

                    }
                    catch (
                            TaskNotFoundException e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 4:

                    historyService
                            .showHistory();

                    break;

                case 5:

                    System.out.println(
                            "Exiting...");

                    return;

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }
}