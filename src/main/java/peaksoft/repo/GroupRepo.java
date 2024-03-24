package peaksoft.repo;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.responce.GroupResponse;
import peaksoft.model.Group;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {

    @Query("select new peaksoft.dto.responce.GroupResponse(g.groupName) from Group g")
    List<GroupResponse> getAllGroups();

    @Query("select new peaksoft.dto.responce.GroupResponse(g.groupName) from Group g where g.id =:id")
    GroupResponse getGroupById(Long id);

    @Query("select count(s) from Group g join g.studentList s where g.id = :groupId")
    Integer countStudentByGroupAndInstructor(Long groupId);
}
