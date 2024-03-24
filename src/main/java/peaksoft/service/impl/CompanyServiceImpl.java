package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.responce.CompanyResponse;
import peaksoft.dto.responce.CompanyWithInfo;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.service.CompanyService;
import peaksoft.repo.CompanyRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor

public class CompanyServiceImpl implements CompanyService  {
    private final CompanyRepo companyRepo;
    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepo.findAllCompanies();
    }

    @Override
    public SimpleResponse createCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepo.save(company);
        return SimpleResponse.builder().
                httpStatus(HttpStatus.OK).
                massage("Company successfully saved").build();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return companyRepo.findCompanyById(id);

    }

    @Override @Transactional
    public SimpleResponse updateCompanyById(Long id, CompanyRequest newCompanyRequest) {
        Company company = new Company();
        company.setName(newCompanyRequest.getName());
        company.setCountry(newCompanyRequest.getCountry());
        company.setAddress(newCompanyRequest.getAddress());
        company.setPhoneNumber(newCompanyRequest.getPhoneNumber());
        companyRepo.save(company);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                massage("Company updated successfully").build();
    }

    @Override
    public SimpleResponse deleteCompanyById(Long id) {
        companyRepo.deleteById(id);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).
                massage("Company with id: " + id + " successfully deleted").build();
    }

    private Company checkId(Long companyId) {
        return companyRepo.findById(companyId)
                .orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + companyId + " not found"));
    }
    @Override
    public CompanyWithInfo companyWithInfo(Long companyId) {
        Company company = checkId(companyId);

        CompanyWithInfo companyWithInfo = companyRepo.companyWithInfo(companyId);


        for (Instructor instructor : company.getInstructorList()) {
            companyWithInfo.addInstructorsName(instructor.getFirstName());
        }
        int count = 0;
        for (Course course : company.getCourseList()) {
            companyWithInfo.addCourseNames(course.getCourseName());
            for (Group group : course.getGroupList()) {
                companyWithInfo.addGroupName(group.getGroupName());
                count += group.getStudentList().size();
            }
        }
        companyWithInfo.setCountOfStudents(count);
        return companyWithInfo;
    }
}
