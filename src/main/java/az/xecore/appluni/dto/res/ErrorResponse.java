package az.xecore.appluni.dto.res;

public record ErrorResponse(
        int status,
        String error,
        String message
) {}