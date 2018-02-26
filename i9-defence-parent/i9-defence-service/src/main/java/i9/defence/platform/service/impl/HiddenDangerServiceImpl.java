package i9.defence.platform.service.impl;

import i9.defence.platform.dao.HiddenDangerDao;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 隐患类型提醒ServiceImpl
* @ClassName: HiddenDangerServiceImpl 
* @Description: TODO
* @author luobo
* @date 2018年1月10日 上午11:23:32 
*
 */
@Service
public class HiddenDangerServiceImpl implements HiddenDangerService {
	
	@Autowired
	private HiddenDangerDao hiddenDangerDao;
	/**
	 * 增加隐患类型
	 */
	@Override
	public void addHiddenDanger(HiddenDanger hiddenDanger)
			throws BusinessException {
			try {
				if (hiddenDanger.getId()!=null) {
					hiddenDangerDao.updateHiddenDanger(hiddenDanger); 
				}else {
					hiddenDangerDao.addHiddenDanger(hiddenDanger);
				}
			} catch (Exception e) {
				throw new BusinessException("添加隐患类型失败",e.getMessage());
			}
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBounds<HiddenDanger> selectByLimitPage(
			HiddenDangerDto hiddenDangerDto) throws BusinessException { 
		try {
			return  hiddenDangerDao.selectByLimitPage(hiddenDangerDto,hiddenDangerDto.getCurrentPage(),hiddenDangerDto.getPageSize());
		} catch (Exception e) {
			  throw new BusinessException("分页查询隐患类型失败",e.getMessage());
		}
	}

	/**
	 * 根据id查询
	 */
	@Override
	public HiddenDanger getHiddenDanger(int id) throws BusinessException {
		try {
			return hiddenDangerDao.getHiddenDanger(id);
		} catch (Exception e) {
			 throw new BusinessException("根据id查询隐患类型失败",e.getMessage());
		}
	}

	@Override
	public void updateHiddenDanger(HiddenDanger hiddenDanger) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
