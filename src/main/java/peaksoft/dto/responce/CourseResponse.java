package peaksoft.dto.responce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

public class CourseResponse {
    private String courseName;
    private String description;

    public CourseResponse(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }
}
