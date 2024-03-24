package peaksoft.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.responce.InstructorInfosResponse;
import peaksoft.dto.responce.InstructorResponse;
import peaksoft.model.Instructor;

import java.util.List;

@Repository


public interface InstructorRepo extends JpaRepository<Instructor, Long> {

    @Query("select new peaksoft.dto.responce.InstructorResponse(" +
            "i.firstName, i.lastName, i.phoneNumber, i.specialization) from Instructor i")
    List<InstructorResponse> findAllInstructors();

    @Query("select new peaksoft.dto.responce.InstructorResponse(" +
            "i.firstName, i.lastName, i.phoneNumber, i.specialization) from Instructor i where i.id = :id")
    InstructorResponse findInstructorById(Long id);

    @Query("select new peaksoft.dto.responce.InstructorInfosResponse(i.id, i.lastName, i.firstName, i.phoneNumber, i.specialization) from Instructor i where i.id =:inId")
    InstructorInfosResponse instructorWIthInfos(Long inId);
}
