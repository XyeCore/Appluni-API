package az.xecore.appluni.repos;

import az.xecore.appluni.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
}