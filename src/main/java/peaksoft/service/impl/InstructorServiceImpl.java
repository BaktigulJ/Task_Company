package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.responce.InstructorInfosResponse;
import peaksoft.dto.responce.InstructorResponse;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.InstructorRepo;
import peaksoft.service.InstructorService;

import java.util.*;

@Service
@RequiredArgsConstructor

public class InstructorServiceImpl  implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;
    private final CompanyRepo companyRepo;

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepo.findAllInstructors();
    }

    @Override
    public SimpleResponse createInstructorAndAssignToCompany(Long companyId, InstructorRequest instructorRequest) {
        Optional<Company> companyOptional = companyRepo.findById(companyId);
        if (companyOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Company not found")
                    .build();
        }

        Company company = companyOptional.get();

        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        instructor.getCompanyList().add(company);

        company.getInstructorList().add(instructor);

        instructorRepo.save(instructor);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Instructor successfully created and assigned to the company")
                .build();
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        return instructorRepo.findInstructorById(id);
    }

    @Override @Transactional
    public SimpleResponse updateInstructorByCompanyId(Long companyId, Long id, InstructorRequest newInstructorRequest) {
        Company company = companyRepo.findById(companyId)
                .orElse(null);
        if (company == null) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Company not found")
                    .build();
        }
        Instructor instructor = instructorRepo.findById(id)
                .orElse(null);
        if (instructor == null) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Instructor not found")
                    .build();
        }

        instructor.setFirstName(newInstructorRequest.getFirstName());
        instructor.setLastName(newInstructorRequest.getLastName());
        instructor.setPhoneNumber(newInstructorRequest.getPhoneNumber());
        instructor.setSpecialization(newInstructorRequest.getSpecialization());

        boolean isCompanyAlreadyAssociated = instructor.getCompanyList().stream()
                .anyMatch(c -> c.getId().equals(companyId));
        if (!isCompanyAlreadyAssociated) {
            instructor.getCompanyList().add(company);
        }
        instructorRepo.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Instructor updated successfully")
                .build();
    }

    @Override
    public SimpleResponse deleteInstructorById(Long id) {
        Optional<Instructor> instructorOptional = instructorRepo.findById(id);
        if (instructorOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .massage("Instructor not found")
                    .build();
        } instructorRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .massage("Instructor with id: "+ id + " deleted successfully")
                .build();
    }
    private Instructor checkId(Long inId){
        return instructorRepo.findById(inId)
                .orElseThrow(() ->
                        new NoSuchElementException("Instructor with id: "+inId+" not found"));
    }
    @Override
    public InstructorInfosResponse instructorWIthInfos(Long inId) {
        instructorRepo.findById(inId)
                .orElseThrow(() ->
                        new NoSuchElementException("Instructor with id: "+inId+" not found"));
        Instructor instructor = checkId(inId);
        InstructorInfosResponse instructorInfosResponse = instructorRepo.instructorWIthInfos(inId);

        Map<String, Integer> groupNameWithStudents = instructorInfosResponse.getGroupNameWithStudent();
        List<Course> courses = instructor.getCourseList();

        for (Course course : courses) {
            for (Group group : course.getGroupList()) {
                int count = 0;
                count += group.getStudentList().size();
                groupNameWithStudents.put(group.getGroupName(), count);
            }
        }
        return instructorInfosResponse;
    }

    @Override @Transactional
    public SimpleResponse assignInstructorToCourse(Long courseId, Long inId) {
        try {
            Instructor instructor = checkId(inId);
            Course course = courseRepo.findById(courseId)
                    .orElseThrow(() ->
                            new NoSuchElementException("Course with id: " + courseId + " not found"));
            instructor.getCourseList().add(course);
            course.setInstructor(instructor);
            return new SimpleResponse(HttpStatus.OK, "successfully assigned Instructor to course");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage(e.getMessage())
                    .build();
        }
    }


}
