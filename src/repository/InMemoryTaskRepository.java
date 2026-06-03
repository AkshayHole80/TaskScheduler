package repository;

import model.Task;
import util.TaskPriorityComparator;

import java.util.Collection;
import java.util.PriorityQueue;

public class InMemoryTaskRepository
        implements TaskRepository {

    private final PriorityQueue<Task> tasks =
            new PriorityQueue<>(
                    new TaskPriorityComparator()
            );

    @Override
    public void save(Task task) {
        tasks.offer(task);
    }

    @Override
    public Collection<Task> findAll() {
        return tasks;
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public PriorityQueue<Task> getTasks() {
        return tasks;
    }
}