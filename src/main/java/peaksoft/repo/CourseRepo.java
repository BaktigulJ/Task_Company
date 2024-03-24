package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.responce.CourseResponse;
import peaksoft.model.Course;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query("select new peaksoft.dto.responce.CourseResponse (c.courseName, c.description) from Course c order by c.dateOfStart")
    List<CourseResponse> findAllCourses();

    @Query("select new peaksoft.dto.responce.CourseResponse (c.courseName, c.description) " +
            "from Course c where c.id = :id")
    CourseResponse findCourseById(Long id);

    @Modifying
    @Query("update Course c set c.courseName = :courseName, c.dateOfStart = :dateOfStart, c.description = :description where c.company.id = :companyId")
    void updateCourseByCompanyId(@Param("companyId") Long companyId, @Param("courseName") String courseName, @Param("dateOfStart") LocalDate dateOfStart, @Param("description") String description);


}
