package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.ScannerImpl;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.responce.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor

public class TaskAPI {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAllTask(){
        return taskService.getAllTasks();
    }

    @PostMapping("/{lessonId}")
    public SimpleResponse saveTask(@PathVariable Long lessonId,
                                   @RequestBody TaskRequest taskRequest){
        return taskService.saveTaskByLessonId(lessonId, taskRequest);
    }
    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable Long id){
       return taskService.getTaskById(id);
    }

    @PutMapping("/{lessonId}/{id}")
    public SimpleResponse updateTask(@PathVariable Long lessonId,
                                     @PathVariable Long id,
                                     @RequestBody TaskRequest newTaskRequest){
        return taskService.updateTaskByLessonId(lessonId,id, newTaskRequest);

    }



}
