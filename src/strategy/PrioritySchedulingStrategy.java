package strategy;

import model.Task;

import java.util.PriorityQueue;

public class PrioritySchedulingStrategy
        implements SchedulingStrategy {

    private final PriorityQueue<Task> taskQueue;

    public PrioritySchedulingStrategy(
            PriorityQueue<Task> taskQueue) {

        this.taskQueue = taskQueue;
    }

    @Override
    public Task getNextTask() {

        return taskQueue.poll();
    }
}