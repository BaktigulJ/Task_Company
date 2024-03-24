package peaksoft.dto.responce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor

public class LessonResponse {
    private String lessonName;

    public LessonResponse(String lessonName) {
        this.lessonName = lessonName;
    }
}
