package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.responce.CompanyResponse;
import peaksoft.dto.responce.CompanyWithInfo;
import peaksoft.model.Company;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> getAllCompanies();

    SimpleResponse createCompany(CompanyRequest companyRequest);

    CompanyResponse getCompanyById(Long id);


    SimpleResponse updateCompanyById(Long id, CompanyRequest newCompanyRequest);

    SimpleResponse deleteCompanyById(Long id);

    CompanyWithInfo companyWithInfo(Long companyId);
}
