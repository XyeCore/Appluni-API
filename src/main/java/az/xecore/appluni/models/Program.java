package az.xecore.appluni.models;

import az.xecore.appluni.utils.Degree;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name="program")
@Data
@Getter
@NoArgsConstructor
public class Program {

    @Id
    @UuidGenerator
    @Column(name = "id",unique = true,updatable = false)
    private String id;
    private String title;
    @Column(name = "degree_level")
    private Degree degreeLevel;

    private String language;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @JsonIgnore
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Application> applications;
}
