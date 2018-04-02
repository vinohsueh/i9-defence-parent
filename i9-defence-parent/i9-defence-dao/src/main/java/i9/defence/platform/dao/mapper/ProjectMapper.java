package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.dao.vo.ProjectSelectDto;
import i9.defence.platform.model.Project;
import i9.defence.platform.model.ProjectExample;

public interface ProjectMapper {
	int countByExample(@Param("example") ProjectSearchDto projectSearchDto);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    List<Project> selectByLimitPage(@Param("example") ProjectSearchDto projectSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
    
    List<ProjectSelectDto> selectAllProjectName(@Param("example") ProjectSearchDto projectSearchDto);
    
    void insertProjectEquipment(@Param("projectId") Integer projectId,@Param("equipmentIds") List<Integer> equipmentIds);

    List<Integer> selectAllEquipmentIds(Integer projectId);
    
  //重置项目的责任人们   之删除
    void deleteClientByProjectId(@Param("projectId") Integer projectId) ;
    
    //重置项目的责任人们   之增加
    void insertIntoClientByProjectId(@Param("projectId") Integer projectId,@Param("clientIds") List<Integer> clientIds);
}