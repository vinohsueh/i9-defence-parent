package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.dao.vo.ErrHandleUnifiedDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

public interface ErrHandleService {
    
    //批量处理设备故障 type = 1 故障 或者 设备报警 type = 2 报警  或者 设备隐患 type = 2 隐患
    void handlingErrors(ErrHandleUnifiedDto errHandleUnifiedDto) throws BusinessException;
    
    /**
     * 删除设备错误处理记录
     * @param kid
     * @throws Exception
     */
    void deleteErrHandle(List<Integer> ids) throws BusinessException;
    
    /**
     * 根据ID获取设备错误处理记录
     * @param id
     * @return
     * @throws Exception
     */
    ErrHandle getErrHandleById(int id) throws BusinessException;
    
    /**
     * 分页查询设备错误处理记录
     * @param errHandleSearchDto
     * @return
     */
    PageBounds<ErrHandle> selectByLimitPage(ErrHandleSearchDto errHandleSearchDto) throws BusinessException;
    
    /**
     * 批量处理问题设备
     * @param list
     * @throws BusinessException
     */
	void handleErrorDevice(List<String> list) throws BusinessException;
	//处理待处理列表状态和处理详情
	void errHandleEdit(ErrHandle errHandle)throws BusinessException;
}
