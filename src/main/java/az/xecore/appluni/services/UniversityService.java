package az.xecore.appluni.services;


import az.xecore.appluni.exceptions.UniversityNotFoundException;
import az.xecore.appluni.models.University;
import az.xecore.appluni.repos.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    @Transactional(readOnly = true)
    public University getUniversityById(String id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new UniversityNotFoundException("University not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<University> getAllUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }
}