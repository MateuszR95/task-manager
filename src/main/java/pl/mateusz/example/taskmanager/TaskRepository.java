package pl.mateusz.example.taskmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.isDone=false ORDER BY t.deadline ASC")
    List<Task> findAllTasksToDo();

    @Query("SELECT t FROM Task t WHERE t.isDone=true")
    List<Task> findAllTasksDone();
    Optional<Task> getTaskById(Long id);
}
