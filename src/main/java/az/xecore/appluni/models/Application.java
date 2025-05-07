package az.xecore.appluni.models;

import az.xecore.appluni.utils.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@Where(clause = "deleted = false") // Soft delete filter
public class Application {
    @Id
    @Column(name = "id", unique = true, updatable = false, length = 16)
    private String id; // Format: "APL-XXXX-XXXX"

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "deleted")
    private boolean deleted = false;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @PrePersist
    protected void onCreate() {
        // Generate user-friendly ID
        if (this.id == null) {
            this.id = generateApplicationId();
        }

        // Set submission time
        this.submittedAt = LocalDateTime.now();

        // Set default status if null
        if (this.status == null) {
            this.status = Status.PENDING;
        }
    }

    private String generateApplicationId() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyMMddHHmm"));

        String randomChars = generateRandomChars(4);

        return "APL-" + timestamp.substring(0,6) + "-" + randomChars;
    }

    private String generateRandomChars(int length) {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"; // Excluded confusing chars
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
        }
        return sb.toString();
    }
}