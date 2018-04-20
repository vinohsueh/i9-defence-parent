package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.HiddenDangerDao;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;
import i9.defence.platform.model.HiddenDangerExample.Criteria;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

@Service
public class HiddenDangerServiceImpl implements HiddenDangerService {

	@Autowired
	private HiddenDangerDao hiddenDangerDao;
	
	@Override
	public PageBounds<HiddenDanger> selectByLimitPage(HiddenDangerExample hiddenDangerExample, int currectPage,
			int pageSize,Integer equipCategoryId) throws BusinessException {
		try {
			Criteria criteria = hiddenDangerExample.createCriteria();
			if(0 !=equipCategoryId && null !=equipCategoryId) {
				criteria.andEquipCategoryIdEqualTo(equipCategoryId);
				return hiddenDangerDao.selectByLimitPage(hiddenDangerExample, currectPage, pageSize);
			}else {
				return hiddenDangerDao.selectByLimitPage(hiddenDangerExample, currectPage, pageSize);
			}
		} catch (Exception e) {
			throw new BusinessException("分页查询隐患类型失败", e.getMessage());
		}
	}

	@Override
	public void insertHiddenDanger(HiddenDanger hiddenDanger) throws BusinessException {
		try {
			if(null==hiddenDanger.getId()||0==hiddenDanger.getId()) {
				hiddenDangerDao.insertHiddenDanger(hiddenDanger);
			}else {
				hiddenDangerDao.updateHiddenDanger(hiddenDanger);
			}
		} catch (Exception e) {
			throw new BusinessException("新增隐患类型失败", e.getMessage());
		}
	}

	@Override
	public void updateHiddenDanger(HiddenDanger hiddenDanger) throws BusinessException {
		try {
			hiddenDangerDao.updateHiddenDanger(hiddenDanger);
		} catch (Exception e) {
			throw new BusinessException("更改隐患类型失败", e.getMessage());
		}
	}

	@Override
	public HiddenDanger getHiddenById(Integer Id) throws BusinessException {
		try {
			return hiddenDangerDao.getHiddenById(Id);
		} catch (Exception e) {
			throw new BusinessException("根据Id查询隐患类型失败", e.getMessage());
		}
	}

	@Override
	public void delHiddenDangers(List<Integer> ids) throws BusinessException {
		try {
			hiddenDangerDao.delHiddenDangers(ids);
		} catch (Exception e) {
			throw new BusinessException("根据Ids删除隐患类型失败", e.getMessage());
		}
	}

	@Override
	public List<HiddenDanger> selectAllHiddendanger() throws BusinessException {
		try {
			return hiddenDangerDao.selectAllHiddendanger();
		} catch (Exception e) {
			throw new BusinessException("查询全部隐患类型失败", e.getMessage());
		}
	}

	@Override
	public List<String> selectErrorEquipmentIds() throws BusinessException {
		try {
			return hiddenDangerDao.selectErrorEquipmentIds();
		} catch (Exception e) {
			throw new BusinessException("查询故障设备失败", e.getMessage());
		}
	}

}
