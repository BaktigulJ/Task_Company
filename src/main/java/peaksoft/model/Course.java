package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    @ManyToOne
    private Company company;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Instructor instructor;

    @OneToMany(cascade = ALL, mappedBy = "course")
    private List<Lesson> lesson;

    @ManyToMany (mappedBy = "courseList",cascade = ALL)
    private List<Group> groupList;
}