package az.xecore.appluni.dto.req;

import jakarta.validation.constraints.NotBlank;

public record UniversityRequestDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Country is required")
        String country,

        @NotBlank(message = "City is required")
        String city
) {}