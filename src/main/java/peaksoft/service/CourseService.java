package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.responce.CourseResponse;

import java.util.List;


public interface CourseService {
    List<CourseResponse> getAllCourses();

    SimpleResponse saveCourseByComId(Long companyId, CourseRequest courseRequest);

    CourseResponse getCourseById(Long id);

    SimpleResponse updateCourseByCompanyId(Long companyId, Long id, CourseRequest newCourseRequest);

    SimpleResponse deleteCourseByCompanyId(Long companyId, Long id);

}
