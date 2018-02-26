package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.ApplyRefuseDto;
import i9.defence.platform.dao.vo.ManagerApplyDto;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.ManagerApplyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ManagerApplyMapper {
    int countByExample(@Param("example") ManagerApplyDto example);

    int deleteByExample(ManagerApplyExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(ManagerApply record);

    int insertSelective(ManagerApply record);

    List<ManagerApply> selectByExample(ManagerApplyExample example);

    ManagerApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManagerApply record, @Param("example") ManagerApplyExample example);

    int updateByExample(@Param("record") ManagerApply record, @Param("example") ManagerApplyExample example);

    int updateByPrimaryKeySelective(ManagerApply record);

    int updateByPrimaryKey(ManagerApply record);
    
    List<ManagerApply> selectByLimitPage(@Param("example") ManagerApplyDto example, @Param("offset") int offset, @Param("limit") int pageSize);

    List<ManagerApply> selectApplysByIds(List<Integer> ids);

    void updateBatchManagerApplys(@Param("applys") List<ManagerApply> managerApplys,@Param("managerId")Integer managerId);
    
    /**
     * 拒绝申请
     * @param applyRefuseDto
     * @param managerId
     */
    void refuseManagerApply(@Param("dto") ApplyRefuseDto applyRefuseDto,@Param("managerId") Integer managerId);
}