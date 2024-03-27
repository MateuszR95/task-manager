package pl.mateusz.example.taskmanager;

public class PastDateException extends RuntimeException{
    public PastDateException(String message) {
        super(message);
    }
}
