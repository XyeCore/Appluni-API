package az.xecore.appluni.controllers;

import az.xecore.appluni.dto.req.UniversityRequestDTO;
import az.xecore.appluni.dto.res.UniversityResponseDTO;
import az.xecore.appluni.services.UniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/universities")
@RequiredArgsConstructor
@Tag(name = "Universities", description = "University management API")
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping
    @Operation(summary = "Get all universities with pagination and filtering")
    public ResponseEntity<Page<UniversityResponseDTO>> getAllUniversities(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            Pageable pageable) {

        return ResponseEntity.ok(
                universityService.searchUniversities(title, country, city, pageable)
                        .map(UniversityResponseDTO::fromEntity)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get university by ID")
    public ResponseEntity<UniversityResponseDTO> getUniversityById(@PathVariable String id) {
        return ResponseEntity.ok(
                UniversityResponseDTO.fromEntity(universityService.getUniversityById(id))
        );
    }

    @PostMapping
    @Operation(summary = "Create new university")
    public ResponseEntity<UniversityResponseDTO> createUniversity(
            @RequestBody UniversityRequestDTO universityDTO) {
        return ResponseEntity.ok(
                UniversityResponseDTO.fromEntity(
                        universityService.createUniversity(universityDTO)
                )
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update university")
    public ResponseEntity<UniversityResponseDTO> updateUniversity(
            @PathVariable String id,
            @RequestBody UniversityRequestDTO universityDTO) {
        return ResponseEntity.ok(
                UniversityResponseDTO.fromEntity(
                        universityService.updateUniversity(id, universityDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete university")
    public ResponseEntity<Void> deleteUniversity(@PathVariable String id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }
}