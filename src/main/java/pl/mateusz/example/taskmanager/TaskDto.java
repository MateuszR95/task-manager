package pl.mateusz.example.taskmanager;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private boolean isDone;

    public TaskDto(Long id, String name, String description, LocalDateTime deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        isDone = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
