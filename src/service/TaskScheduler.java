package service;

import exception.TaskNotFoundException;
import model.Task;
import repository.TaskRepository;
import strategy.SchedulingStrategy;

import java.util.logging.Logger;

public class TaskScheduler {

    private static final Logger logger =
            Logger.getLogger(TaskScheduler.class.getName());

    private final SchedulingStrategy strategy;
    private final TaskRepository repository;

    public TaskScheduler(
            SchedulingStrategy strategy,
            TaskRepository repository) {

        this.strategy = strategy;
        this.repository = repository;
    }

    public void addTask(Task task) {

        repository.save(task);

        logger.info(
                "Task added : " + task.getName()
        );
    }

    public Task getNextTask() {

        Task task = strategy.getNextTask();

        if(task == null) {

            throw new TaskNotFoundException(
                    "No tasks available"
            );
        }

        logger.info(
                "Task selected : " + task.getName()
        );

        return task;
    }

    public void viewTasks() {

        repository
                .findAll()
                .forEach(System.out::println);
    }
}