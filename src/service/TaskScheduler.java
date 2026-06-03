package service;

import model.Task;
import utils.TaskPriorityComparator;

import java.util.PriorityQueue;

public class TaskScheduler {

    private PriorityQueue<Task> taskQueue;

    public TaskScheduler() {
        taskQueue = new PriorityQueue<>(new TaskPriorityComparator());
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
        System.out.println("Task Added : " + task.getName());
    }

    public Task getNextTask() {
        return taskQueue.poll();
    }

    public boolean hasTasks() {
        return !taskQueue.isEmpty();
    }

    public void viewTasks() {

        if (taskQueue.isEmpty()) {
            System.out.println("No Tasks Available");
            return;
        }

        System.out.println("\nPending Tasks:");

        for (Task task : taskQueue) {
            System.out.println(task);
        }
    }
}