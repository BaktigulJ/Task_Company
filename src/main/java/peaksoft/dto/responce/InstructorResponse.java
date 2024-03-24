package peaksoft.dto.responce;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Specialization;

@Getter @Setter
@NoArgsConstructor

public class InstructorResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Specialization specialization;

    public InstructorResponse(String firstName, String lastName, String phoneNumber, Specialization specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
