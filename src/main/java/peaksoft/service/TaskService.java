package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.responce.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTasks();

    SimpleResponse saveTaskByLessonId(Long lessonId, TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    SimpleResponse updateTaskByLessonId(Long lessonId, Long id, TaskRequest newTaskRequest);
}
