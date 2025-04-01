
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private static final String FILE_PATH = "tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<Task>();
        loadTasks();
    }
    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    private boolean isValidReminderTime(LocalDateTime reminderTime) {
        return reminderTime.isAfter(LocalDateTime.now());
    }

public void checkReminder() {
        LocalDateTime now = LocalDateTime.now();
        for (Task task : tasks) {
            if (task.getReminderTime() != null && !task.isCompleted()) {
                if (now.isAfter(task.getReminderTime())) {
                    System.out.println("⏰ Напоминание! Задача: \"" + task.getDescription() + "\"");
                }
            }
        }
}
    public void setReminderTime(int index, LocalDateTime reminderTime) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Неверный номер задачи!");
            return;
        }
        tasks.get(index).setReminderTime(reminderTime);
        System.out.println("Напоминание установлено!");
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean isCompleted = line.startsWith("Completed");
                String description = line.substring(4);
                Task task = new Task(description);
                task.setCompleted(isCompleted);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден, Создаем новый...");
        }
    }

    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач!");
        }
    }

    public void addTask(String description, LocalDateTime reminderTime) {
        Task task = new Task(description);
        if (reminderTime != null) {
            if (!isValidReminderTime(reminderTime)) {
                System.out.println("Ошибка: дата напоминания должна быть в будущем!");
                return;
            }
            task.setReminderTime(reminderTime);
        }
        tasks.add(task);
        System.out.println("Задача добавлена!");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("⚠️ Список задач пуст. Добавьте первую задачу!");
            return;
        }
        System.out.println("Список задач:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markAsCompleted(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Не верный номер задачи");
            return;
        }
        Task task = tasks.get(index);
        task.setCompleted(true);
        System.out.println("Задача выполнена " + task.getDescription());
    }

    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Не верный номер задачи");
            return;
        }
        Task task = tasks.remove(index);
        System.out.println("Задача удалена " + task.getDescription());

    }
}
