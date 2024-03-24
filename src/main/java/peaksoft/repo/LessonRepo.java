package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.responce.LessonResponse;
import peaksoft.model.Lesson;

import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {

    @Query("select new peaksoft.dto.responce.LessonResponse(l.lessonName) from Lesson l")
    List<LessonResponse> findAllLessons();

    @Query("select new peaksoft.dto.responce.LessonResponse(l.lessonName) from Lesson l where l.id =:id")
    LessonResponse findLessonById(Long id);

}
