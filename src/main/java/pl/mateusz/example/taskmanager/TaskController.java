package pl.mateusz.example.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/form")
    public String displayAddingForm() {
        return "form";
    }
    @PostMapping("/add")
    public String addTask(TaskDto taskDto) {
        taskService.add(taskDto);
        return "success";
    }
    @GetMapping("/list")
    public String displayList(Model model) {
        List<TaskDto> allTasksToDo = taskService.getAllTasksToDo();
        model.addAttribute("allTasks", allTasksToDo);
        return "list";
    }

    @GetMapping("/archive")
    public String displayArchive(Model model) {
        List<TaskDto> doneTasks = taskService.getAllTasksDone();
        model.addAttribute("doneTasks", doneTasks);
        return "archive";
    }

    @PostMapping("/task/{id}/update")
    public String updateTaskStatus(@PathVariable Long id) {
        taskService.updateStatus(id, true);
        return "redirect:/";
    }

    @GetMapping("/task/{id}/edit")
    public String displayEditionForm(@PathVariable Long id, Model model) {
        Optional<Task> optionalTask = taskService.getTaskById(id);
        Task task = optionalTask.orElseThrow(TaskNotFoundException::new);
        model.addAttribute("task", task);
        return "editform";
    }

    @PostMapping("/task/{id}/edit")
    public String editTask(@PathVariable Long id, String name, String description, LocalDateTime deadline) {
        Optional<Task> optionalTask = taskService.getTaskById(id);
        Task task = optionalTask.orElseThrow(TaskNotFoundException::new);
        taskService.editTask(task.getId(), name, description, deadline);
        return "redirect:/";

    }
}
