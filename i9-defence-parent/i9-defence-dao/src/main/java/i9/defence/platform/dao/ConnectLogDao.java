package i9.defence.platform.dao;

import i9.defence.platform.dao.vo.ConnectLogDto;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.utils.PageBounds;

/** 
* 创建时间：2018年5月11日 下午5:01:00
* @author  lby
* @version  
* 
*/
public interface ConnectLogDao {
	
	/**
	 * 添加离线掉线记录
	 * @param connectLog
	 */
    void add(ConnectLog connectLog);
    
   //分页查询设备连接日志
	PageBounds<ConnectLogDto> selectByLimitPage(ConnectLogDto connectLogDto, int currentPage, int pageSize);
}
