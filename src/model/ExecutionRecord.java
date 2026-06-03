package model;

import enums.ExecutionResult;

import java.time.LocalDateTime;

public class ExecutionRecord {

    private final String taskName;
    private final ExecutionResult result;
    private final LocalDateTime executionTime;

    public ExecutionRecord(
            String taskName,
            ExecutionResult result,
            LocalDateTime executionTime) {

        this.taskName = taskName;
        this.result = result;
        this.executionTime = executionTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public ExecutionResult getResult() {
        return result;
    }

    public LocalDateTime getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {

        return taskName +
                " | " +
                result +
                " | " +
                executionTime;
    }
}