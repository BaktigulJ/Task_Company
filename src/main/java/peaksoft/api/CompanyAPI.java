package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.responce.CompanyResponse;
import peaksoft.dto.responce.CompanyWithInfo;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor

public class CompanyAPI {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> allCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public SimpleResponse saveCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.createCompany(companyRequest);
    }

    @GetMapping("/{id}")
    public CompanyResponse getComById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }


    @PutMapping ("/{id}")
    public SimpleResponse updateCompanyById (@PathVariable Long id,
                                              @RequestBody CompanyRequest newCompanyRequest){
        return  companyService.updateCompanyById(id, newCompanyRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteCompany(@PathVariable Long id){
        return companyService.deleteCompanyById(id);
    }

    @GetMapping("/comInfo/{companyId}")
    public CompanyWithInfo companyWithInfo(@PathVariable Long companyId){
        return companyService.companyWithInfo(companyId);
    }
}

