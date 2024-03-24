package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.responce.AllStudentResponse;
import peaksoft.dto.responce.StudentResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.model.Group;
import peaksoft.model.Student;
import peaksoft.repo.GroupRepo;
import peaksoft.repo.StudentRepo;
import peaksoft.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepo.findAllStudents();
    }

    @Override
    public SimpleResponse saveStudentByGroupId(Long groupId, StudentRequest studentRequest) {
        Optional<Group> groupRepoById = groupRepo.findById(groupId);
        if(groupRepoById.isEmpty()){
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).
                    massage("Group is not found").build();
        }
        Group group =groupRepoById.get();
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());

        student.setGroup(group);
        studentRepo.save(student);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                massage("Student saved successfully").build();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepo.findStudentById(id);
    }

    @Override @Transactional
    public SimpleResponse updateStudentByGroupId(Long groupId, Long id, StudentRequest newStudentRequest) {
        Optional<Group> groupRepoById = groupRepo.findById(groupId);
        if(groupRepoById.isEmpty()){
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).
                    massage("Group is not found").build();
        }
        Optional<Student> studentRepoById = studentRepo.findById(id);
        if(studentRepoById.isEmpty()){
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).
                    massage("Student is not found").build();
        }
        Group group =groupRepoById.get();
        Student student = studentRepoById.get();
        student.setFirstName(newStudentRequest.getFirstName());
        student.setLastName(newStudentRequest.getLastName());
        student.setPhoneNumber(newStudentRequest.getPhoneNumber());
        student.setEmail(newStudentRequest.getEmail());
        student.setStudyFormat(newStudentRequest.getStudyFormat());
        student.setGroup(group);
        studentRepo.save(student);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                massage("Student updated successfully").build();
    }

    @Override
    public List<AllStudentResponse> sortByFormat(StudyFormat studyFormat) {
        return studentRepo.sortByFormat(studyFormat);
    }

    private Student checkId(Long studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new NoSuchElementException("student with id: " + studentId + " not found"));
    }

//    @Override @Transactional
//    public SimpleResponse blockStudent(Long studentId) {
//        try {
//            Student student = checkId(studentId);
//            boolean block = student.isBlock();
//            if (block) {
//                student.setBlock(false);
//                return new SimpleResponse(HttpStatus.OK, "successfully blocked");
//            } else {
//                student.setBlock(true);
//                return new SimpleResponse(HttpStatus.OK, "successfully unBlocked");
//            }
//        } catch (Exception e) {
//            return SimpleResponse
//                    .builder()
//                    .httpStatus(HttpStatus.NOT_FOUND)
//                    .massage(e.getMessage())
//                    .build();
//        }
//    }
}
