package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.responce.CompanyResponse;
import peaksoft.dto.responce.CompanyWithInfo;
import peaksoft.model.Company;

import java.util.List;

@Repository

public interface CompanyRepo extends JpaRepository<Company, Long> {

    @Query("select new peaksoft.dto.responce.CompanyResponse(c.name, c.country) from Company c")
    List<CompanyResponse> findAllCompanies();


    @Query ("select new peaksoft.dto.responce.CompanyResponse(c.name, c.country) from Company c where c.id = :id")
    CompanyResponse findCompanyById(Long id);

    @Query("select new peaksoft.dto.responce.CompanyWithInfo(c.id, c.name, c.country, c.address, c.phoneNumber) from Company c where c.id =:companyId")
    CompanyWithInfo companyWithInfo(Long companyId);
}
