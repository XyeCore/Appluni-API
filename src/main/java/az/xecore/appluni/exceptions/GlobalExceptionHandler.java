//package az.xecore.appluni.exceptions;
//
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ProgramNotFoundException.class)
//    @ApiResponse(responseCode = "404", description = "Program not found",
//            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
//    public ResponseEntity<ErrorResponse> handleProgramNotFound(ProgramNotFoundException ex) {
//        ErrorResponse response = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                "Program Not Found",
//                ex.getMessage()
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//    }
//
//    // Other exception handlers...
//
//    record ErrorResponse(int status, String error, String message) {}
//}

// TODO: Fix Swagger