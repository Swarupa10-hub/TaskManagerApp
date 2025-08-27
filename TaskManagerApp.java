import java.util.Scanner;

// Custom Exception for invalid task index
class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

// Task class
class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return name + (isCompleted ? " [Done]" : " [Pending]");
    }
}

// TaskManager class
class TaskManager {
    private Task[] tasks;
    private int count;

    public TaskManager(int size) {
        tasks = new Task[size];
        count = 0;
    }

    public void addTask(String name) {
        if (count < tasks.length) {
            tasks[count++] = new Task(name);
            System.out.println("Task added: " + name);
        } else {
            System.out.println("Task list is full!");
        }
    }

    public void completeTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= count) {
            throw new TaskNotFoundException("Invalid Task Index!");
        }
        tasks[index].markCompleted();
        System.out.println("Task marked as completed: " + tasks[index].getName());
    }

    public void viewTasks() {
        if (count == 0) {
            System.out.println("No tasks available!");
            return;
        }
        System.out.println("Your Tasks:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + ". " + tasks[i]);
        }
    }
}

// Main class
public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager(10); // max 10 tasks
        int choice;

        do {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter task name: ");
                        String name = sc.nextLine();
                        manager.addTask(name);
                        break;
                    case 2:
                        System.out.print("Enter task index: ");
                        int index = sc.nextInt();
                        manager.completeTask(index);
                        break;
                    case 3:
                        manager.viewTasks();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (TaskNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        } while (choice != 4);

        sc.close();
    }
}
