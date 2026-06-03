# Smart Task Scheduler

A console-based Java application that schedules and executes tasks based on priority.

## Features

* Add Tasks
* View Pending Tasks
* Execute Tasks
* Retry Failed Tasks
* View Execution History
* Custom Exception Handling
* Priority-Based Scheduling

## Technologies Used

* Java
* Collections Framework
* PriorityQueue
* Git

## Design Patterns

* Strategy Pattern
* Factory Pattern
* Repository Pattern

## Project Structure

```text
src
├── model
├── service
├── repository
├── strategy
├── retry
├── factory
├── exception
├── enums
├── util
└── Main.java
```

## How It Works

1. User creates a task with a priority.
2. Task is stored in a PriorityQueue.
3. Scheduler selects the next task based on priority.
4. TaskExecutor executes the task.
5. RetryManager retries failed executions.
6. Execution history is recorded and can be viewed later.

## Running the Project

### Prerequisites

* JDK 21 or later
* IntelliJ IDEA (or any Java IDE)

### Steps

1. Clone the repository

```bash
git clone https://github.com/AkshayHole80/TaskScheduler.git
```

2. Open the project in IntelliJ IDEA.

3. Build the project.

4. Run:

```text
src/Main.java
```

5. Use the menu options to add, execute and monitor tasks.

## Sample Flow

```text
1. Add Task
2. View Tasks
3. Execute Next Task
4. View History
5. Exit
```

## Future Improvements

* Database Persistence
* Concurrent Task Execution
* Time-Based Scheduling
* REST API Support
