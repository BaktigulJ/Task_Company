package peaksoft.dto.responce;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CompanyResponse {
        @Column(unique = true)
        private String name;
        private String country;

        public CompanyResponse(String name, String country) {
                this.name = name;
                this.country = country;
        }
}
