package peaksoft.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
}
