package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.responce.LessonResponse;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.LessonRepo;
import peaksoft.service.LessonService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;

    @Override
    public List<LessonResponse> getAllLessons() {
        return lessonRepo.findAllLessons();
    }

    @Override
    public SimpleResponse saveLessonByCourseId(Long courseId, LessonRequest lessonRequest) {
        Optional<Course> courseRepoById = courseRepo.findById(courseId);
        if(courseRepoById.isEmpty()){
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                    massage("course not found").build();
        }
        Course course= courseRepoById.get();
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setCourse(course);
        lessonRepo.save(lesson);

        return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                massage("Lesson saved successfully").build();
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        return lessonRepo.findLessonById(id);
    }

    @Override @Transactional
    public SimpleResponse updateLessonByCourseId(Long courseId, Long id, LessonRequest newLessonRequest) {
        Optional<Course> courseRepoById = courseRepo.findById(courseId);
        if(courseRepoById.isEmpty()){
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                    massage("course not found").build();
        }
        Optional<Lesson> lessonRepoById = lessonRepo.findById(id);
        if(lessonRepoById.isEmpty()){
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                    massage("Lesson not found").build();
        }
        Course course= courseRepoById.get();
        Lesson lesson = lessonRepoById.get();
        lesson.setLessonName(newLessonRequest.getLessonName());
        lesson.setCourse(course);
        lessonRepo.save(lesson);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                massage("Lesson updated successfully").build();

    }
}
