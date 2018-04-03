package i9.defence.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.HiddenDangerDao;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

@Service
public class HiddenDangerServiceImpl implements HiddenDangerService {

	@Autowired
	private HiddenDangerDao hiddenDangerDao;
	
	@Override
	public PageBounds<HiddenDanger> selectByLimitPage(HiddenDangerExample hiddenDangerExample, int currectPage,
			int pageSize) throws BusinessException {
		try {
			return hiddenDangerDao.selectByLimitPage(hiddenDangerExample, currectPage, pageSize);
		} catch (Exception e) {
			throw new BusinessException("分页查询隐患类型失败", e.getMessage());
		}
	}

	@Override
	public void insertHiddenDanger(HiddenDanger hiddenDanger) throws BusinessException {
		try {
			hiddenDangerDao.insertHiddenDanger(hiddenDanger);
		} catch (Exception e) {
			throw new BusinessException("新增隐患类型失败", e.getMessage());
		}
	}

}
