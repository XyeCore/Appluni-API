package az.xecore.appluni.controllers;

import az.xecore.appluni.models.Application;
import az.xecore.appluni.services.ApplicationService;
import az.xecore.appluni.utils.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
@Tag(name = "Applications", description = "API for managing applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Create new application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application created",
                    content = @Content(schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Application already exists")
    })
    public ResponseEntity<Application> createApplication(
            @RequestParam String userId,
            @RequestParam String programId) {
        return ResponseEntity.ok(applicationService.createApplication(userId, programId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get application by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application found",
                    content = @Content(schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "404", description = "Application not found")
    })
    public ResponseEntity<Application> getApplication(@PathVariable String id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping
    @Operation(summary = "Get applications with filtering and pagination",
            parameters = {
                    @Parameter(name = "userId", in = ParameterIn.QUERY, description = "Filter by user ID"),
                    @Parameter(name = "programId", in = ParameterIn.QUERY, description = "Filter by program ID"),
                    @Parameter(name = "status", in = ParameterIn.QUERY, description = "Filter by status"),
                    @Parameter(name = "page", in = ParameterIn.QUERY, description = "Page number"),
                    @Parameter(name = "size", in = ParameterIn.QUERY, description = "Page size"),
                    @Parameter(name = "sort", in = ParameterIn.QUERY, description = "Sorting criteria")
            })
    public ResponseEntity<Page<Application>> getApplications(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String programId,
            @RequestParam(required = false) Status status,
            @PageableDefault Pageable pageable) {

        if (userId != null) {
            return ResponseEntity.ok(applicationService.getUserApplications(userId, pageable));
        } else if (programId != null) {
            return ResponseEntity.ok(applicationService.getProgramApplications(programId, pageable));
        } else if (status != null) {
            return ResponseEntity.ok(applicationService.getApplicationsByStatus(status, pageable));
        }

        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update application status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status updated",
                    content = @Content(schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "400", description = "Invalid status transition"),
            @ApiResponse(responseCode = "404", description = "Application not found")
    })
    public ResponseEntity<Application> updateStatus(
            @PathVariable String id,
            @RequestParam Status status) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete application")
    @ApiResponse(responseCode = "204", description = "Application deleted")
    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}