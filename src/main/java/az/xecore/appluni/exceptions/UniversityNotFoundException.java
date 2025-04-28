package az.xecore.appluni.exceptions;

public class UniversityNotFoundException extends RuntimeException {
    public UniversityNotFoundException(String message) {
        super(message);
    }
}