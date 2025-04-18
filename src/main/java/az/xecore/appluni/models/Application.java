package az.xecore.appluni.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
public class Application {
    @Id
    @UuidGenerator
    @Column(name = "id",unique = true,updatable = false)
    private String id;

    private String status; // e.g. pending, submitted, accepted, rejected
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

}
