package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_gen", sequenceName = "group_seq", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    @ManyToMany(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Course> courseList;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> studentList;

}
