package peaksoft.dto.responce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

public class GroupResponse {
    private String groupName;

    public GroupResponse(String groupName) {
        this.groupName = groupName;
    }
}
