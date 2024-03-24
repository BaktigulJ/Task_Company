package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.responce.AllStudentResponse;
import peaksoft.dto.responce.StudentResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentAPI {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> allStudents (){
       return studentService.getAllStudents();
    }

    @PostMapping("/{groupId}")
    public SimpleResponse saveStudent(@PathVariable Long groupId,
                                      @RequestBody StudentRequest studentRequest){
        return studentService.saveStudentByGroupId(groupId,studentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{groupId}/{id}")
    public SimpleResponse updateStudentByGroupId (@PathVariable Long groupId,
                                                  @PathVariable Long id,
                                                  @RequestBody StudentRequest newStudentRequest){
        return studentService.updateStudentByGroupId(groupId, id, newStudentRequest);
    }

    @GetMapping("/sortByFormat")
    public List<AllStudentResponse> sortByFormat(@RequestParam StudyFormat studyFormat){
        return studentService.sortByFormat(studyFormat);
    }

   /* @PostMapping("/block/{studentId}")
    public SimpleResponse blockStudent(@PathVariable Long studentId){
        return studentService.blockStudent(studentId);
    }*/





}
