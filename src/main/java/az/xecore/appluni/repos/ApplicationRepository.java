package az.xecore.appluni.repos;

import az.xecore.appluni.models.Application;
import az.xecore.appluni.models.Program;
import az.xecore.appluni.models.User;
import az.xecore.appluni.utils.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {
    @Query("SELECT a FROM Application a WHERE a.user.id = :userId AND a.deleted = false")
    Page<Application> findAllByUserId(@Param("userId") String userId, Pageable pageable);

    @Query("SELECT a FROM Application a WHERE a.program.id = :programId AND a.deleted = false")
    Page<Application> findAllByProgramId(@Param("programId") String programId, Pageable pageable);

    @Query("SELECT a FROM Application a WHERE a.status = :status AND a.deleted = false")
    Page<Application> findAllByStatus(@Param("status") Status status, Pageable pageable);

    @Query("SELECT a FROM Application a WHERE a.id = :id AND a.deleted = false")
    Optional<Application> findByIdAndNotDeleted(@Param("id") String id);

    boolean existsByUserAndProgramAndDeletedFalse(User user, Program program);

    //existsByUserAndProgramAndDeletedFalse

}