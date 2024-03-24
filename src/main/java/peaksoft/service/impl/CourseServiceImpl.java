package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.responce.CourseResponse;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final CompanyRepo companyRepo;

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepo.findAllCourses();
    }

    @Override
    public SimpleResponse saveCourseByComId(Long companyId, CourseRequest courseRequest) {
        Optional<Company> companyRepoById = companyRepo.findById(companyId);
        if (companyRepoById.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Company not found")
                    .build();
        }
        Company company= companyRepoById.get();
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setCompany(company);
        company.getCourseList().add(course);
        courseRepo.save(course);
        return SimpleResponse.builder().
                httpStatus(HttpStatus.OK).
                massage("Course saved successfully").build();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        return  courseRepo.findCourseById(id);

    }

    @Override
    @Transactional
    public SimpleResponse updateCourseByCompanyId(Long companyId, Long id, CourseRequest newCourseRequest) {
        Optional<Company> companyById = companyRepo.findById(companyId);
        if (companyById.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Company not found")
                    .build();
        }
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Course not found")
                    .build();
        }

        Company company = companyById.get();
        Course course = courseOptional.get();

        course.setCourseName(newCourseRequest.getCourseName());
        course.setDateOfStart(newCourseRequest.getDateOfStart());
        course.setDescription(newCourseRequest.getDescription());

        if (!course.getCompany().getId().equals(companyId)) {
            course.setCompany(company);
        }
        courseRepo.save(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Course updated successfully")
                .build();
    }

    @Override
    public SimpleResponse deleteCourseByCompanyId(Long companyId, Long id) {
        Optional<Company> companyById = companyRepo.findById(companyId);
        if (companyById.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .massage("Company not found")
                    .build();
        }
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .massage("Course not found")
                    .build();
        } courseRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .massage("Course with id: "+ id + " deleted successfully")
                .build();
    }

}
