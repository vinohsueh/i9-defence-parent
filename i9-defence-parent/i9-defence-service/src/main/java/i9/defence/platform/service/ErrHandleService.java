package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

public interface ErrHandleService {
	/**
     * 添加设备错误处理记录
     * @param project
     * @throws Exception
     */
    void addErrHandle(ErrHandle errHandle) throws BusinessException;
    
    /**
     * 更新设备错误处理记录
     * @param project
     * @throws Exception
     */
    void updateErrHandle(ErrHandle errHandle) throws BusinessException;
    
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
}
