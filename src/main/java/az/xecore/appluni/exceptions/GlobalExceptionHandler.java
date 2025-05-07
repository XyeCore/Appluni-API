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
//@ExceptionHandler(UniversityNotFoundException.class)
//public ProblemDetail handleUniversityNotFound(UniversityNotFoundException ex) {
//    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//            HttpStatus.NOT_FOUND,
//            ex.getMessage()
//    );
//    problemDetail.setTitle("University Not Found");
//    return problemDetail;
//}
//
//@ExceptionHandler(UniversityAlreadyExistsException.class)
//public ProblemDetail handleUniversityAlreadyExists(UniversityAlreadyExistsException ex) {
//    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//            HttpStatus.CONFLICT,
//            ex.getMessage()
//    );
//    problemDetail.setTitle("University Already Exists");
//    return problemDetail;
//}
//    // Other exception handlers...
//
//    record ErrorResponse(int status, String error, String message) {}
//}

// TODO: Fix Swagger