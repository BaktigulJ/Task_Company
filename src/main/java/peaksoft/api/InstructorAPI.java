package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.responce.InstructorInfosResponse;
import peaksoft.dto.responce.InstructorResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor

public class InstructorAPI {
    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorResponse> getAllInstructors(){
        return instructorService.getAllInstructors();
    }


    @PostMapping("/create/{companyId}")
    public SimpleResponse createInstructorAndAssignToCompany(@PathVariable Long companyId,
                                                             @RequestBody InstructorRequest instructorRequest) {
        return instructorService.createInstructorAndAssignToCompany(companyId, instructorRequest);
    }

    @GetMapping("/{id}")
    public InstructorResponse getInstructorByIdById(@PathVariable Long id){
        return instructorService.getInstructorById(id);
    }

    @PutMapping("/{companyId}/{id}")
    public SimpleResponse updateInstructorByComId(@PathVariable Long companyId,
                                              @PathVariable Long id,
                                              @RequestBody InstructorRequest newInstructorRequest){
        return instructorService.updateInstructorByCompanyId(companyId,id, newInstructorRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteInstructorById(@PathVariable Long id){
        return instructorService.deleteInstructorById(id);
    }


    @GetMapping("/instructorInfo/{inId}")
    public InstructorInfosResponse instructorWithInfos(@PathVariable Long inId){
        return instructorService.instructorWIthInfos(inId);
    }

    @PostMapping("/assignToCourse/{courseId}/{inId}")
    public SimpleResponse assignIntoCourse(@PathVariable Long courseId,
                                           @PathVariable Long inId){
        return instructorService.assignInstructorToCourse(courseId, inId);
    }


}
