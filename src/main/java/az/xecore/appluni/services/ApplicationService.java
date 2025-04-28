package az.xecore.appluni.services;

import az.xecore.appluni.exceptions.ApplicationNotFoundException;
import az.xecore.appluni.exceptions.BusinessLogicException;
import az.xecore.appluni.models.Application;
import az.xecore.appluni.models.Program;
import az.xecore.appluni.models.User;
import az.xecore.appluni.repos.ApplicationRepository;
import az.xecore.appluni.utils.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UsersService userService;
    private final ProgramService programService;

    @Transactional
    public Application createApplication(String userId, String programId) {
        User user = userService.getUserById(userId);
        Program program = programService.getProgramById(programId);

        if (applicationRepository.existsByUserAndProgramAndDeletedFalse(user, program)) {
            throw new BusinessLogicException("User already has application for this program");
        }

        Application application = new Application();
        application.setUser(user);
        application.setProgram(program);
        application.setStatus(Status.PENDING);
        application.setSubmittedAt(LocalDateTime.now());

        return applicationRepository.save(application);
    }

    @Transactional(readOnly = true)
    public Page<Application> getUserApplications(String userId, Pageable pageable) {
        return applicationRepository.findAllByUserId(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Application> getProgramApplications(String programId, Pageable pageable) {
        return applicationRepository.findAllByProgramId(programId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Application> getApplicationsByStatus(Status status, Pageable pageable) {
        return applicationRepository.findAllByStatus(status, pageable);
    }

    @Transactional(readOnly = true)
    public Application getApplicationById(String applicationId) {
        return applicationRepository.findByIdAndNotDeleted(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));
    }

    @Transactional
    public Application updateApplicationStatus(String applicationId, Status status) {
        Application application = getApplicationById(applicationId);

        if (!application.getStatus().canTransitionTo(status)) {
            throw new BusinessLogicException(
                    String.format("Invalid status transition from %s to %s",
                            application.getStatus(), status)
            );
        }

        application.setStatus(status);
        return applicationRepository.save(application);
    }

    @Transactional
    public void deleteApplication(String applicationId) {
        Application application = getApplicationById(applicationId);
        application.setDeleted(true);
        applicationRepository.save(application);
    }
}