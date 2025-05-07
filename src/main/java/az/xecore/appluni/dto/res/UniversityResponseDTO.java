package az.xecore.appluni.dto.res;

import az.xecore.appluni.models.University;

public record UniversityResponseDTO(
        String id,
        String title,
        String country,
        String city
) {
    public static UniversityResponseDTO fromEntity(University university) {
        return new UniversityResponseDTO(
                university.getId(),
                university.getTitle(),
                university.getCountry(),
                university.getCity()
        );
    }
}