package factory;

import enums.Priority;
import model.Task;
import model.TaskType;

public class TaskFactory {

    private TaskFactory() {}

    public static Task createTask(
            int id,
            String name,
            Priority priority,
            TaskType type) {

        return new Task(
                id,
                name,
                priority,
                type
        );
    }
}