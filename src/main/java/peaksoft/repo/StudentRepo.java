package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.responce.AllStudentResponse;
import peaksoft.dto.responce.StudentResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.model.Student;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("select new peaksoft.dto.responce.StudentResponse(s.firstName, s.lastName, s.phoneNumber) from Student s")
    List<StudentResponse> findAllStudents();

    @Query("select new peaksoft.dto.responce.StudentResponse(s.firstName, " +
            "s.lastName, s.phoneNumber) from Student s where s.id =:id")
    StudentResponse findStudentById(Long id);

    @Query("select new peaksoft.dto.responce.AllStudentResponse(s.id, s.lastName, s.firstName, s.phoneNumber, s.email, s.studyFormat) from Student s where s.studyFormat =:studyFormat")
    List<AllStudentResponse> sortByFormat(StudyFormat studyFormat);

}
