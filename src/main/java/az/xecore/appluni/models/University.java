package az.xecore.appluni.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class University {

    @Id
    @UuidGenerator
    @Column(name = "id",unique = true,updatable = false)
    private String id;

    private String title;
    private String country;
    private String city;


    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Program> programs;
}
