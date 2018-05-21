package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.MonthDataDto;
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

  //重置项目的安全责任人们   之修改 safe字段都为0  初始化
    void updateSafeZeroByProjectId(@Param("projectId") Integer projectId);
    
  //重置项目的安全责任人们   之修改 指定的managerId safe字段为1  初始化
    void updateSafeOneByProjectId(@Param("projectId") Integer projectId,@Param("safeIds") List<Integer> safeIds);

	List<Project> selectProject(@Param("example") ProjectSearchDto projectSearchDto);

	//后台首页 查询全部项目
    List<Project> findAllProjectIndex(@Param("example") ProjectSearchDto projectSearchDto);

  //根据 项目的 省市区  查询项目ID
  	List<Integer> selectIdsByMonthDataDto(@Param("example") MonthDataDto monthDataDto);

	List<Project> findProjectName();
}