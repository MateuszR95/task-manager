package pl.mateusz.example.taskmanager;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException() {
        super("Nie znaleziono takiego zadania");
    }
}
