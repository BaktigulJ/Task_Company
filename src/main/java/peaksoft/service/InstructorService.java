package peaksoft.service;

import jakarta.transaction.Transactional;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.responce.InstructorInfosResponse;
import peaksoft.dto.responce.InstructorResponse;

import java.util.List;

public interface InstructorService {
    List<InstructorResponse> getAllInstructors();

    SimpleResponse createInstructorAndAssignToCompany(Long companyId, InstructorRequest instructorRequest);

    InstructorResponse getInstructorById(Long id);

    SimpleResponse updateInstructorByCompanyId(Long companyId, Long id, InstructorRequest newInstructorRequest);

    SimpleResponse deleteInstructorById(Long id);

    InstructorInfosResponse instructorWIthInfos(Long inId);

    @Transactional
    SimpleResponse assignInstructorToCourse(Long courseId, Long inId);
}
