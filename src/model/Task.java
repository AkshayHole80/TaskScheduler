package model;

import enums.Priority;
import enums.TaskStatus;

public class Task {

    private final int id;
    private final String name;
    private final Priority priority;
    private TaskType taskType;

    private TaskStatus status;
    private int retryCount;

    public Task(
            int id,
            String name,
            Priority priority,
            TaskType taskType) {

        this.id = id;
        this.name = name;
        this.priority = priority;
        this.taskType = taskType;

        this.status = TaskStatus.PENDING;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void incrementRetryCount() {
        retryCount++;
    }

    @Override
    public String toString() {

        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", retryCount=" + retryCount +
                '}';
    }
}