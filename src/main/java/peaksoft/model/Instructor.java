package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Specialization;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_gen")
    @SequenceGenerator(name = "instructor_gen", sequenceName = "instructor_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(name = "specialization", columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @ManyToMany(mappedBy = "instructorList", cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Company> companyList= new ArrayList<>();

    @OneToMany(mappedBy = "instructor" , cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Course> courseList;
}
