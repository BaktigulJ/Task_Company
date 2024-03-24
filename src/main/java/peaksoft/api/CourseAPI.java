package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.responce.CourseResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("api/course")
@RequiredArgsConstructor

public class CourseAPI {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> allCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/{companyId}")
    public SimpleResponse saveCourse(@PathVariable Long companyId,
                                     @RequestBody CourseRequest courseRequest) {
        return courseService.saveCourseByComId(companyId, courseRequest);
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @PutMapping("/{companyId}/{id}")
    public SimpleResponse updateCourseByComId(@PathVariable Long companyId,
                                              @PathVariable Long id,
                                              @RequestBody CourseRequest newCourseRequest){
        return courseService.updateCourseByCompanyId(companyId,id, newCourseRequest);
    }

    @DeleteMapping("/{companyId}/{id}")
    public SimpleResponse deleteCourseByComId(@PathVariable Long companyId,
                                              @PathVariable Long id){
        return courseService.deleteCourseByCompanyId(companyId, id);
    }

}
