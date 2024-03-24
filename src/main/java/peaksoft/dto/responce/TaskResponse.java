package peaksoft.dto.responce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
@NoArgsConstructor

public class TaskResponse {

    private String taskName;
    private String taskText;
    private LocalDate deadLine;

    public TaskResponse(String taskName, String taskText, LocalDate deadLine) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }
}
