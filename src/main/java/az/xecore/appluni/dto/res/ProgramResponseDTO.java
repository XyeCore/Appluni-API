package az.xecore.appluni.dto.res;

import az.xecore.appluni.models.Program;
import az.xecore.appluni.models.University;
import az.xecore.appluni.utils.Degree;

public record ProgramResponseDTO(
        String id,
        String title,
        Degree degreeLevel,
        String language,
        University university
) {
    public static ProgramResponseDTO fromEntity(Program program) {
        return new ProgramResponseDTO(
                program.getId(),
                program.getTitle(),
                program.getDegreeLevel(),
                program.getLanguage(),
                program.getUniversity()
        );
    }
}