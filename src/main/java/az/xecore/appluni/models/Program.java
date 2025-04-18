package az.xecore.appluni.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name="program")
@Data
@NoArgsConstructor
public class Program {

    @Id
    @UuidGenerator
    @Column(name = "id",unique = true,updatable = false)
    private String id;
    private String title;
    private String degree_level;

    private String language;
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Application> applications;
}
