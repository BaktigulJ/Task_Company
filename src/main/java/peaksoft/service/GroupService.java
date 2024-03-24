package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.responce.GroupResponse;

import java.util.List;

public interface GroupService {
    List<GroupResponse> getAllGroups();

    SimpleResponse saveGroupAndAssignToCourse(Long courseId, GroupRequest groupRequest);

    GroupResponse getGroupById(Long id);

    SimpleResponse updateGroupByCourseId(Long courseId, Long id, GroupRequest groupRequest);

    Integer countStudent(Long groupId);

}
