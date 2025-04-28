package az.xecore.appluni.controllers;

import az.xecore.appluni.dto.req.ProgramRequestDTO;
import az.xecore.appluni.dto.res.ProgramResponseDTO;
import az.xecore.appluni.services.ProgramService;
import az.xecore.appluni.utils.Degree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/programs")
@RequiredArgsConstructor
@Tag(name = "Programs", description = "Academic program management")
public class ProgramController {
    private final ProgramService programService;

    @GetMapping
    @Operation(summary = "Get all programs with pagination and filtering")
    public ResponseEntity<Page<ProgramResponseDTO>> getAllPrograms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Degree degreeLevel,
            @RequestParam(required = false) String language,
            Pageable pageable) {

        return ResponseEntity.ok(
                programService.searchPrograms(title, degreeLevel, language, pageable)
                        .map(ProgramResponseDTO::fromEntity)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get program by ID")
    public ResponseEntity<ProgramResponseDTO> getProgramById(@PathVariable String id) {
        return ResponseEntity.ok(
                ProgramResponseDTO.fromEntity(programService.getProgramById(id))
        );
    }

    @PostMapping
    @Operation(summary = "Create new program")
    public ResponseEntity<ProgramResponseDTO> createProgram(
            @Valid @RequestBody ProgramRequestDTO programDTO) {
        return ResponseEntity.ok(
                ProgramResponseDTO.fromEntity(
                        programService.createProgram(programDTO)
                )
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update program")
    public ResponseEntity<ProgramResponseDTO> updateProgram(
            @PathVariable String id,
            @Valid @RequestBody ProgramRequestDTO programDTO) {
        return ResponseEntity.ok(
                ProgramResponseDTO.fromEntity(
                        programService.updateProgram(id, programDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete program")
    public ResponseEntity<Void> deleteProgram(@PathVariable String id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}