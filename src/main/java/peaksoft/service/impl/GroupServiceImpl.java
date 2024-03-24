package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.responce.GroupResponse;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.GroupRepo;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;
    private final CourseRepo courseRepo;

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepo.getAllGroups();
    }

    @Override
    public SimpleResponse saveGroupAndAssignToCourse(Long courseId, GroupRequest groupRequest) {
        Optional<Course> courseOptional = courseRepo.findById(courseId);
        if (courseOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Group not found")
                    .build();
        }

        Course course = courseOptional.get();

        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());

        group.setCourseList(new ArrayList<>());

        group.getCourseList().add(course);

        course.getGroupList().add(group);

        groupRepo.save(group);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Group successfully created and assigned to the course")
                .build();
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        return groupRepo.getGroupById(id);
    }

    @Override @Transactional
    public SimpleResponse updateGroupByCourseId(Long courseId, Long id, GroupRequest groupRequest) {
        Optional<Course> courseOptional = courseRepo.findById(courseId);
        if (courseOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Course not found")
                    .build();
        }
        Optional<Group> groupOptional = groupRepo.findById(courseId);
        if (groupOptional.isEmpty()) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .massage("Group not found")
                    .build();
        }
        Group group = groupOptional.get();

        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());

        groupRepo.save(group);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Group updated successfully")
                .build();
    }

    @Override
    public Integer countStudent(Long groupId) {
        return groupRepo.countStudentByGroupAndInstructor(groupId);
    }


}

