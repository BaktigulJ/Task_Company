package peaksoft.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

@NoArgsConstructor
@Getter
@Setter
public class AllStudentResponse {
    private Long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;

    public AllStudentResponse(Long id, String lastName, String firstName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
