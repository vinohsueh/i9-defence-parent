package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.model.ErrHandleExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErrHandleMapper {
    int countByExample(@Param("example") ErrHandleSearchDto errHandleSearchDto);

    int deleteByExample(ErrHandleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ErrHandle record);

    int insertSelective(ErrHandle record);

    List<ErrHandle> selectByExample(ErrHandleExample example);

    ErrHandle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ErrHandle record, @Param("example") ErrHandleExample example);

    int updateByExample(@Param("record") ErrHandle record, @Param("example") ErrHandleExample example);

    int updateByPrimaryKeySelective(ErrHandle record);

    int updateByPrimaryKey(ErrHandle record);
    
    List<ErrHandle> selectByLimitPage(@Param("example") ErrHandleSearchDto errHandleSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

  //批量处理设备故障 type = 1 故障
    void updateHandleFault(String eqDeviceId);
    
    //批量处理设备报警 type = 2 报警
    void updateHandlePolice(String eqDeviceId);
    
  //批量处理设备隐患 type = 2 隐患
    void updateHandleHidden(String eqDeviceId);
    
    //批量处理故障
	void updateBatchHandleFault(List<String> list);
	
	//批量处理报警
	void updateBatchHandlePolice(List<String> list);
	
	//批量处理隐患
	void updateBatchHandleHidden(List<String> list);
	
	/**
	 * 批量添加记录
	 * @param list
	 */
	void addBatchHandle(List<String> list);
}