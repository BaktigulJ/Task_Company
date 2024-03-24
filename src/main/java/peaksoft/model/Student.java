package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.StudyFormat;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column(name = "study_format", columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
  //  private boolean block = true;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Group group;
}
