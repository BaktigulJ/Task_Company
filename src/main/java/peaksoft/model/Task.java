package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen", sequenceName = "task_seq", allocationSize = 1)
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadLine;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Lesson lesson;

}
