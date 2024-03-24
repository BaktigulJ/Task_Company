package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.dto.request.CompanyRequest;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "company_instructor",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private List<Instructor> instructorList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Course> courseList;

}
