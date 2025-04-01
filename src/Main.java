import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        while (true) {
            System.out.println("\n--- To-Do Менеджер ---");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Просмотреть задачи");
            System.out.println("3. Отметить задачу как выполненную");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Установить напоминание");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите описание задачи: ");
                    String description = scanner.nextLine();

                    System.out.print("Добавить напоминание? (y/n): ");
                    String addReminder = scanner.nextLine();
                    LocalDateTime reminderTime = null;

                    if (addReminder.equalsIgnoreCase("y")) {
                        System.out.print("Введите дату и время (дд.мм.гггг чч:мм): ");
                        String dateInput = scanner.nextLine();
                        try {
                            reminderTime = LocalDateTime.parse(dateInput, dateFormatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Ошибка формата даты! Напоминание не установлено.");
                        }
                    }

                    taskManager.addTask(description, reminderTime);
                    break;

                case 2:
                    taskManager.viewTasks();
                    break;

                case 3:
                    if (taskManager.hasTasks()) {
                        System.out.print("Введите номер задачи для отметки: ");
                        int completeIndex = scanner.nextInt() - 1;
                        taskManager.markAsCompleted(completeIndex);
                    } else {
                        System.out.println("⚠️ Нет задач для отметки!");
                    }
                    break;

                case 4:
                    if (taskManager.hasTasks()) {
                        System.out.print("Введите номер задачи для удаления: ");
                        int deleteIndex = scanner.nextInt() - 1;
                        taskManager.removeTask(deleteIndex);
                    } else {
                        System.out.println("⚠️ Нет задач для удаления!");
                    }
                    break;

                case 5:
                    taskManager.saveTasks();
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Неверный выбор!");
            }
            taskManager.checkReminder();
        }
    }
}