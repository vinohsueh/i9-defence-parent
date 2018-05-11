package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EqSystemCategoryDao;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.service.EqSystemCategoryService;
import i9.defence.platform.utils.BusinessException;
/**
 * 类别ServiceImpl
 * @author gbq
 * @create 2018年5月9日
 */
@Service
@Transactional
public class EqSystemCategoryServiceImpl implements EqSystemCategoryService {
	@Autowired
	private EqSystemCategoryDao eqSystemCategoryDao;

	@Override
	public List<EquipmentSystemtype> findEquipmentSystemCategory() throws BusinessException {
		try {
			return eqSystemCategoryDao.findEquipmentSystemCategory();
		} catch (Exception e) {
			throw new BusinessException("查询项目类别失败",e.getMessage());
		}
	}

}
