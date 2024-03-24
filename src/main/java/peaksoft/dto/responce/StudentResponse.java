package peaksoft.dto.responce;

import jdk.jfr.SettingDefinition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

public class StudentResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public StudentResponse(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
