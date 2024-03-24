package peaksoft.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.dto.SimpleResponse;

@Getter @Setter
@NoArgsConstructor

public class GroupRequest {
    private String groupName;
    private String imageLink;
    private String description;

    public GroupRequest(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }

}
