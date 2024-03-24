package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.responce.TaskResponse;
import peaksoft.model.Task;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long > {

    @Query("select new peaksoft.dto.responce.TaskResponse(t.taskName, t.taskText, t.deadLine) from Task t")
    List<TaskResponse> getAllTasks();

    @Query("select new peaksoft.dto.responce.TaskResponse(t.taskName, t.taskText, t.deadLine) from Task t where t.id =:id")
    TaskResponse findTaskById(Long id);
}
