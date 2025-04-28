package az.xecore.appluni.models;

import az.xecore.appluni.utils.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@Where(clause = "deleted = false") // Soft delete filter
public class Application {
    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = Status.PENDING; // Default status
        }
    }
}