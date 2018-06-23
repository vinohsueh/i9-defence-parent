package i9.defence.platform.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ConnectLogDao;
import i9.defence.platform.dao.vo.ConnectLogDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ConnectLogService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
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
	@Autowired
	private ManagerService managerService;
	
	@Override
	public PageBounds<ConnectLogDto> selectByLimitPage(ConnectLogDto connectLogDto) throws BusinessException {
		try {
			//当前登录人
			Manager loginManager = managerService.getLoginManager();
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				return connectLogDao.selectByLimitPage(connectLogDto, connectLogDto.getCurrentPage(), connectLogDto.getPageSize());
			}
			//如果为经销商和管理员(type=1)
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				connectLogDto.setManagerId(loginManager.getId());
				return connectLogDao.selectByLimitPage(connectLogDto, connectLogDto.getCurrentPage(), connectLogDto.getPageSize());
			}//如果是项目管理员(type=2)
			else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				connectLogDto.setManagerId2(loginManager.getId()); 
				return connectLogDao.selectByLimitPage(connectLogDto, connectLogDto.getCurrentPage(), connectLogDto.getPageSize());
			}
		} catch (Exception e) {
			throw new BusinessException("分页查询项目类别失败",e.getMessage());
		}
		return null; 
	}


}
