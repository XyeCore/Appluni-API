package az.xecore.appluni.services;

import az.xecore.appluni.dto.req.ProgramRequestDTO;
import az.xecore.appluni.exceptions.ProgramNotFoundException;
import az.xecore.appluni.models.Program;
import az.xecore.appluni.repos.ProgramRepository;
import az.xecore.appluni.utils.Degree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;
    private final UniversityService universityService;

    @Transactional
    public Program createProgram(ProgramRequestDTO programDTO) {
        Program program = new Program();
        program.setTitle(programDTO.title());
        program.setDegreeLevel(programDTO.degreeLevel());
        program.setLanguage(programDTO.language());
        program.setUniversity(universityService.getUniversityById(programDTO.universityId()));

        return programRepository.save(program);
    }
    @Transactional(readOnly = true)
    public Page<Program> getAllPrograms(Pageable pageable) {
        return programRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Program getProgramById(String id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found with id: " + id));
    }

    @Transactional
    public Program updateProgram(String id, ProgramRequestDTO programDTO) {
        Program program = getProgramById(id);

        program.setTitle(programDTO.title());
        program.setDegreeLevel(programDTO.degreeLevel());
        program.setLanguage(programDTO.language());

        if (!program.getUniversity().getId().equals(programDTO.universityId())) {
            program.setUniversity(universityService.getUniversityById(programDTO.universityId()));
        }

        return programRepository.save(program);
    }

    @Transactional
    public void deleteProgram(String id) {
        Program program = getProgramById(id);
        programRepository.delete(program);
    }

    @Transactional(readOnly = true)
    public Page<Program> searchPrograms(String title, Degree degreeLevel, String language, Pageable pageable) {
        return programRepository.searchPrograms(title, degreeLevel, language, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Program> getProgramsByUniversity(String universityId, Pageable pageable) {
        return programRepository.findByUniversityId(universityId, pageable);
    }
}