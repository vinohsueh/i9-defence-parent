package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.utils.PageBounds;

public interface ErrHandleDao {
	/**
     * 添加设备错误处理记录
     * @param project
     * @throws Exception
     */
    void addErrHandle(ErrHandle errHandle) throws Exception;
    
    /**
     * 更新设备错误处理记录
     * @param project
     * @throws Exception
     */
    void updateErrHandle(ErrHandle errHandle) throws Exception;
    
    /**
     * 删除设备错误处理记录
     * @param kid
     * @throws Exception
     */
    void deleteErrHandle(List<Integer> ids) throws Exception;
    
    /**
     * 根据ID获取设备错误处理记录
     * @param id
     * @return
     * @throws Exception
     */
    ErrHandle getErrHandleById(int id) throws Exception;
    
    /**
     * 分页查询设备错误处理记录
     * @param errHandleSearchDto
     * @return
     */
    PageBounds<ErrHandle> selectByLimitPage(ErrHandleSearchDto errHandleSearchDto) throws Exception;

    //批量处理设备故障 type = 2 报警
    void updateHandleFault(List<String> eqDeviceIds) throws Exception;
    
/*    //批量处理设备报警 type = 2 报警
    void updateHandlePolice(String eqDeviceId) throws Exception;*/
    
    //批量处理设备隐患 type = 3 隐患
    void updateHandleHidden(List<String> eqDeviceIds) throws Exception;

	void updateBatchHandleFault(List<String> list);

	void updateBatchHandlePolice(List<String> list);

	void updateBatchHandleHidden(List<String> list);

	void addBatchHandle(List<String> list);
	
	/**
	 * 批量插入历史记录
	* @Title: addErrHandle 
	* @Description: TODO
	* @param errHandles
	* @throws Exception
	 */
	void addErrHandle(List<ErrHandle> errHandles) throws Exception;
	
	
	/**
	 * 批量更改历史记录
	* @Title: updateErrHandles 
	* @Description: TODO
	* @param errHandles
	* @throws Exception
	 */
	void updateErrHandles(List<ErrHandle> errHandles) throws Exception;
}
