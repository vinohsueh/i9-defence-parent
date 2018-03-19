package i9.defence.platform.dao;

import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.utils.PageBounds;

/**
 * @author gbq
 * @create 2018年3月19日
 */
public interface StreamOriginDao {
	 /**
     * 分页
     * @param StreamOriginSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<StreamOrigin> selectByLimitPage(StreamOriginSearchDto streamOriginSearchDto,
    		int currectPage, int pageSize) throws Exception;
}
