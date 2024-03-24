package peaksoft.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.dto.SimpleResponse;

@Setter
@Getter
@NoArgsConstructor

public class LessonRequest {
    private String lessonName;

    public LessonRequest(String lessonName) {
        this.lessonName = lessonName;
    }


}
