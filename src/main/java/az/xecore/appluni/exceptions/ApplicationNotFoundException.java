package az.xecore.appluni.exceptions;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(String id) {
        super("Application not found with id: " + id);
    }
}