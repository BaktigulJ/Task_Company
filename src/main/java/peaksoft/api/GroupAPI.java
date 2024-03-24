package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.responce.GroupResponse;
import peaksoft.model.Group;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupAPI {
    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> allGroups (){
        return groupService.getAllGroups();
    }

    @PostMapping("/{courseId}")
    public SimpleResponse saveGroupAndAssignToCourse(@PathVariable Long courseId,
                                                     @RequestBody GroupRequest groupRequest){
        return groupService.saveGroupAndAssignToCourse(courseId, groupRequest);
    }

    @GetMapping("/{id}")
    public GroupResponse getGroupById (@PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @PutMapping("/{courseId}/{id}")
    public SimpleResponse updateGroupByCourseId(@PathVariable Long courseId,
                                                @PathVariable Long id,
                                                @RequestBody GroupRequest groupRequest){
        return groupService.updateGroupByCourseId(courseId,id, groupRequest);
    }

    @GetMapping("/count/{groupId}")
    public Integer countStudentByInstructorGroup(@PathVariable Long groupId){
        return groupService.countStudent(groupId);
    }
}
