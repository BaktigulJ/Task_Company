package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.responce.TaskResponse;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repo.LessonRepo;
import peaksoft.repo.TaskRepo;
import peaksoft.service.LessonService;
import peaksoft.service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TaskServiceImpl implements TaskService  {
    private final TaskRepo taskRepo;
    private final LessonRepo lessonRepo;
    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepo.getAllTasks();
    }

    @Override
    public SimpleResponse saveTaskByLessonId(Long lessonId, TaskRequest taskRequest) {
        Optional<Lesson> lessonRepoById = lessonRepo.findById(lessonId);
        if(lessonRepoById.isEmpty()){
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Lesson not found")
                    .build();
        }
        Lesson lesson = lessonRepoById.get();
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadLine(taskRequest.getDeadLine());
        task.setLesson(lesson);
        taskRepo.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("The task successfully saved")
                .build();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return taskRepo.findTaskById(id);
    }

    @Override @Transactional
    public SimpleResponse updateTaskByLessonId(Long lessonId, Long id, TaskRequest newTaskRequest) {
        Optional<Lesson> lessonRepoById = lessonRepo.findById(lessonId);
        if(lessonRepoById.isEmpty()){
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Lesson not found")
                    .build();
        }
        Optional<Task> taskRepoById = taskRepo.findById(id);
        if(taskRepoById.isEmpty()){
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("task not found")
                    .build();
        }
        Lesson lesson = lessonRepoById.get();
        Task task = taskRepoById.get();
        task.setTaskName(newTaskRequest.getTaskName());
        task.setTaskText(newTaskRequest.getTaskText());
        task.setDeadLine(newTaskRequest.getDeadLine());
        task.setLesson(lesson);
        taskRepo.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("task successfully updated")
                .build();
    }
}
