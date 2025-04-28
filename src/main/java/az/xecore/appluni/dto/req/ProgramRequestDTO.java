package az.xecore.appluni.dto.req;

import az.xecore.appluni.utils.Degree;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProgramRequestDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Degree level is required")
        Degree degreeLevel,

        @NotBlank(message = "Language is required")
        String language,

        @NotBlank(message = "University ID is required")
        String universityId
) {}