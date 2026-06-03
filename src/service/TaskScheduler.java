package service;

import exception.TaskNotFoundException;
import model.Task;
import strategy.SchedulingStrategy;
import utils.TaskPriorityComparator;

import java.util.PriorityQueue;

public class TaskScheduler {

    private final PriorityQueue<Task> taskQueue;

    private final SchedulingStrategy
            schedulingStrategy;

    public TaskScheduler(
            SchedulingStrategy schedulingStrategy,
            PriorityQueue<Task> taskQueue) {

        this.schedulingStrategy =
                schedulingStrategy;

        this.taskQueue = taskQueue;
    }

    public void addTask(Task task) {

        taskQueue.offer(task);

        System.out.println(
                "Task Added : "
                        + task.getName());
    }

    public Task getNextTask() {

        Task task =
                schedulingStrategy.getNextTask();

        if(task == null) {

            throw new TaskNotFoundException(
                    "No tasks available."
            );
        }

        return task;
    }

    public void viewTasks() {

        if(taskQueue.isEmpty()) {

            System.out.println(
                    "No pending tasks.");
            return;
        }

        System.out.println(
                "\n===== PENDING TASKS =====");

        taskQueue.forEach(System.out::println);
    }
}