import enums.Priority;
import model.Task;
import service.TaskExecutor;
import service.TaskScheduler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TaskScheduler scheduler = new TaskScheduler();
        TaskExecutor executor = new TaskExecutor();

        int taskIdCounter = 1;

        while (true) {

            System.out.println("\n===== TASK SCHEDULER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Execute Next Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Task Name: ");
                    String taskName = scanner.nextLine();

                    System.out.println("Select Priority:");
                    System.out.println("1. HIGH");
                    System.out.println("2. MEDIUM");
                    System.out.println("3. LOW");

                    int priorityChoice = scanner.nextInt();

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
                            System.out.println("Invalid Priority.");
                            continue;
                    }

                    Task task = new Task(
                            taskIdCounter++,
                            taskName,
                            priority
                    );

                    scheduler.addTask(task);

                    break;

                case 2:

                    scheduler.viewTasks();

                    break;

                case 3:

                    if (!scheduler.hasTasks()) {
                        System.out.println("No tasks available.");
                        break;
                    }

                    Task nextTask = scheduler.getNextTask();

                    executor.execute(nextTask);

                    break;

                case 4:

                    System.out.println("Exiting Task Scheduler...");
                    scanner.close();
                    return;

                default:

                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}