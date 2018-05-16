package i9.defence.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ConnectLogDao;
import i9.defence.platform.dao.vo.ConnectLogDto;
import i9.defence.platform.service.ConnectLogService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 类别ServiceImpl
 * @author gbq
 * @create 2018年5月16日
 */
@Service
@Transactional
public class ConnectLogServiceImpl implements ConnectLogService{
	@Autowired
	private ConnectLogDao connectLogDao;

	@Override
	public PageBounds<ConnectLogDto> selectByLimitPage(ConnectLogDto connectLogDto) throws BusinessException {
		try {
			return connectLogDao.selectByLimitPage(connectLogDto, connectLogDto.getCurrentPage(), connectLogDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页查询项目类别失败",e.getMessage());
		}
	}


}
