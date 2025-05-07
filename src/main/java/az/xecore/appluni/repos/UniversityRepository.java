package az.xecore.appluni.repos;

import az.xecore.appluni.models.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
    @Query("SELECT u FROM University u WHERE " +
            "(:title IS NULL OR LOWER(u.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:country IS NULL OR LOWER(u.country) = LOWER(:country)) AND " +
            "(:city IS NULL OR LOWER(u.city) = LOWER(:city))")
    Page<University> searchUniversities(
            @Param("title") String title,
            @Param("country") String country,
            @Param("city") String city,
            Pageable pageable);

    boolean existsByTitleAndCountryAndCity(String title, String country, String city);
}