package service;

import enums.TaskStatus;
import model.Task;

public class TaskExecutor {

    public void execute(Task task) {

        if (task == null) {
            System.out.println("No task found.");
            return;
        }

        task.setStatus(TaskStatus.RUNNING);

        System.out.println("\nExecuting : " + task.getName());

        task.setStatus(TaskStatus.COMPLETED);

        System.out.println("Completed : " + task.getName());
    }
}