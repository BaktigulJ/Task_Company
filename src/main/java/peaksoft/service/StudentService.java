package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.responce.AllStudentResponse;
import peaksoft.dto.responce.StudentResponse;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();

    SimpleResponse saveStudentByGroupId(Long groupId, StudentRequest studentRequest);

    StudentResponse getStudentById(Long id);

    SimpleResponse updateStudentByGroupId(Long groupId, Long id, StudentRequest newStudentRequest);

    List<AllStudentResponse> sortByFormat(StudyFormat studyFormat);

   // SimpleResponse blockStudent(Long studentId);

}
