package az.xecore.appluni.services;

import az.xecore.appluni.dto.req.UniversityRequestDTO;
import az.xecore.appluni.exceptions.UniversityAlreadyExistsException;
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
    public Page<University> getAllUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public University getUniversityById(String id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new UniversityNotFoundException("University not found with id: " + id));
    }

    @Transactional
    public University createUniversity(UniversityRequestDTO universityDTO) {
        if (universityRepository.existsByTitleAndCountryAndCity(
                universityDTO.title(),
                universityDTO.country(),
                universityDTO.city())) {
            throw new UniversityAlreadyExistsException("University already exists with these details");
        }

        University university = new University();
        university.setTitle(universityDTO.title());
        university.setCountry(universityDTO.country());
        university.setCity(universityDTO.city());

        return universityRepository.save(university);
    }

    @Transactional
    public University updateUniversity(String id, UniversityRequestDTO universityDTO) {
        University university = getUniversityById(id);

        university.setTitle(universityDTO.title());
        university.setCountry(universityDTO.country());
        university.setCity(universityDTO.city());

        return universityRepository.save(university);
    }

    @Transactional
    public void deleteUniversity(String id) {
        University university = getUniversityById(id);
        universityRepository.delete(university);
    }

    @Transactional(readOnly = true)
    public Page<University> searchUniversities(String title, String country, String city, Pageable pageable) {
        return universityRepository.searchUniversities(title, country, city, pageable);
    }
}