package repository;

import model.Task;

import java.util.Collection;

public interface TaskRepository {

    void save(Task task);

    Collection<Task> findAll();

    boolean isEmpty();
}