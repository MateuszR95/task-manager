package pl.mateusz.example.taskmanager;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void add(TaskDto taskDto) {
        Task task = new Task(taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getDeadline(),
                taskDto.isDone());
        taskRepository.save(task);
    }

    public List<TaskDto> getAllTasksToDo() {
        return taskRepository.findAllTasksToDo()
                .stream().map(task -> new TaskDto(task.getId(), task.getName(),
                        task.getDescription(), task.getDeadline()))
                .collect(Collectors.toList());
    }

    public List<TaskDto> getAllTasksDone() {
        return taskRepository.findAllTasksDone()
                .stream().map(task -> new TaskDto(task.getId(), task.getName(),
                        task.getDescription(), task.getDeadline()))
                .collect(Collectors.toList());
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Transactional
    public void updateStatus(Long taskId, boolean isDone) {
        Optional<Task> optionalTask = taskRepository.getTaskById(taskId);
        Task task = optionalTask.orElseThrow(TaskNotFoundException::new);
        task.setDone(isDone);
        taskRepository.save(task);
    }

    @Transactional
    public void editTask(Long id, String name, String description, LocalDateTime deadline) {
        Optional<Task> optionalTask = getTaskById(id);
        Task task = optionalTask.orElseThrow(TaskNotFoundException::new);
        task.setName(name);
        task.setDescription(description);
        task.setDeadline(deadline);
        taskRepository.save(task);
    }
}
