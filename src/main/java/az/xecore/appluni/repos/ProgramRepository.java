package az.xecore.appluni.repos;

import az.xecore.appluni.models.Program;
import az.xecore.appluni.utils.Degree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String> {

    @Query("SELECT p FROM Program p WHERE p.university.id = :universityId")
    Page<Program> findByUniversityId(@Param("universityId") String universityId, Pageable pageable);

    Page<Program> findByDegreeLevel(Degree degreeLevel, Pageable pageable);

    Page<Program> findByLanguage(String language, Pageable pageable);

    @Query("SELECT p FROM Program p WHERE " +
            "(:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:degreeLevel IS NULL OR p.degreeLevel = :degreeLevel) AND " +
            "(:language IS NULL OR p.language = :language)")
    Page<Program> searchPrograms(
            @Param("title") String title,
            @Param("degreeLevel") Degree degreeLevel,
            @Param("language") String language,
            Pageable pageable);
}