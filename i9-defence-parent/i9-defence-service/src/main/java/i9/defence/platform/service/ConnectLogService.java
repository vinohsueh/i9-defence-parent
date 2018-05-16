package i9.defence.platform.service;

import i9.defence.platform.dao.vo.ConnectLogDto;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

public interface ConnectLogService {
	//分页查询设备连接日志
	PageBounds<ConnectLogDto> selectByLimitPage(ConnectLogDto connectLogDto)throws BusinessException;

}
