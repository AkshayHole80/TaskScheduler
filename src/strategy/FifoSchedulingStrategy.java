package strategy;

import model.Task;

import java.util.Queue;

public class FifoSchedulingStrategy
        implements SchedulingStrategy {

    private final Queue<Task> taskQueue;

    public FifoSchedulingStrategy(
            Queue<Task> taskQueue) {

        this.taskQueue = taskQueue;
    }

    @Override
    public Task getNextTask() {

        return taskQueue.poll();
    }
}