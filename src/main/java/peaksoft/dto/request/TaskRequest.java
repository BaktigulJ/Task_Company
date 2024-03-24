package peaksoft.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

public class TaskRequest {

    private String taskName;
    private String taskText;
    private LocalDate deadLine;

    public TaskRequest(String taskName, String taskText, LocalDate deadLine) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }
}
