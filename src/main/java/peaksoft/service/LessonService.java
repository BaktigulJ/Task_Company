package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.responce.LessonResponse;

import java.util.List;

public interface LessonService {
    List<LessonResponse> getAllLessons();

    SimpleResponse saveLessonByCourseId(Long courseId, LessonRequest lessonRequest);

    LessonResponse getLessonById(Long id);

    SimpleResponse updateLessonByCourseId(Long courseId, Long id, LessonRequest newLessonRequest);
}
