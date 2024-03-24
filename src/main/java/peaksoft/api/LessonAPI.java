package peaksoft.api;

import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.responce.LessonResponse;
import peaksoft.service.LessonService;

import javax.swing.plaf.IconUIResource;
import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor

public class LessonAPI {
    private final LessonService lessonService;

    @GetMapping
    public List<LessonResponse> allLessons (){
        return lessonService.getAllLessons();
    }

    @PostMapping("/{courseId}")
    public SimpleResponse saveLesson(@PathVariable Long courseId,
                                     @RequestBody LessonRequest lessonRequest){
        return lessonService.saveLessonByCourseId(courseId, lessonRequest);
    }

    @GetMapping("/{id}")
    public  LessonResponse getLessonById(@PathVariable Long id){
        return lessonService.getLessonById(id);
    }

    @PutMapping("/{courseId}/{id}")
    public SimpleResponse updateLessonByCourseId(@PathVariable Long courseId,
                                                 @PathVariable Long id,
                                                 @RequestBody LessonRequest newLessonRequest){
        return lessonService.updateLessonByCourseId(courseId, id, newLessonRequest);
    }




}
