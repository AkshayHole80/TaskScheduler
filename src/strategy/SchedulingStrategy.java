package strategy;

import model.Task;

public interface SchedulingStrategy {

    Task getNextTask();

}