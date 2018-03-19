package i9.defence.platform.service;

import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * Service
 * @author gbq
 * @create 2018年3月19日
 */
public interface StreamOriginService {
	  /**
     * 分页查询项目
     * @param streamOriginSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<StreamOrigin> selectByLimitPage(StreamOriginSearchDto streamOriginSearchDto) throws BusinessException;
    
    /**
     * 添加原始数据
     * @param streamOrigin
     */
	void addStreamOrigin(StreamOrigin streamOrigin) throws BusinessException;
}
