import java.time.LocalDateTime;

public class Task {
    private String description;

    private boolean isCompleted;

    private LocalDateTime createdAt;

    private LocalDateTime reminderTime;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        this.createdAt = LocalDateTime.now(); // Автоматически ставим текущее время
        this.reminderTime = null;
    }
    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "Выполнено" : "Не выполнено";
        String dateInfo = " (Создано: " + createdAt.toLocalDate() + ")";
        String reminderInfo = reminderTime != null ?
                " ⏰ Напомнить: " + reminderTime.toLocalDate() + " " + reminderTime.toLocalTime() : "";
        return status + description + dateInfo + reminderInfo;
    }
}
