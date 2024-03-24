package peaksoft.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
@NoArgsConstructor

public class CourseRequest {
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    public CourseRequest(String courseName, LocalDate dateOfStart, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}
