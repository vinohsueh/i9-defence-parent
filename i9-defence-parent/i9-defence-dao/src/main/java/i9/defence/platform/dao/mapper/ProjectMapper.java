package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.ProjectSearchDto;
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
}