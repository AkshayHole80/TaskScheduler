package model;

import enums.Priority;
import enums.TaskStatus;

public class Task {

    private int id;
    private String name;
    private Priority priority;
    private TaskStatus status;

    public Task(int id, String name, Priority priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}