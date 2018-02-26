package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EquipmentCategoryDao;
import i9.defence.platform.dao.vo.EqCategorySearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;
/**
 * 项目类别ServiceImpl
 * @author gbq
 * @create 2018年1月8日
 */
@Service
@Transactional
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {

	@Autowired
	private EquipmentCategoryDao eqCategoryDao;
	
	@Override
	public PageBounds<EquipmentCategory> selectByLimitPage(EqCategorySearchDto eqCategorySearchDto)
			throws BusinessException {
		try {
			return eqCategoryDao.selectByLimitPage(eqCategorySearchDto, eqCategorySearchDto.getCurrentPage(), eqCategorySearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页查询项目类别失败",e.getMessage());
		}
	}

	@Override
	public void addEqCategory(EquipmentCategory eqCategory) throws BusinessException {
		try {
			if(eqCategory.getId()!=null) {
				eqCategoryDao.updateEqCategory(eqCategory);
			}else {
				eqCategoryDao.addEqCategory(eqCategory);
			}
		} catch (Exception e) {
			throw new BusinessException("添加项目类别失败",e.getMessage());
		}
	}

	@Override
	public void updateEqCategory(EquipmentCategory eqCategory) throws BusinessException {
		try {
			eqCategoryDao.updateEqCategory(eqCategory);
		} catch (Exception e) {
			throw new BusinessException("更新项目类别失败",e.getMessage());
		}
	}

	@Override
	public EquipmentCategory getEqCategoryById(int id) throws BusinessException {
		try {
			return eqCategoryDao.getEqCategoryById(id);
		} catch (Exception e) {
			throw new BusinessException("查询项目类别失败",e.getMessage());
		}
	}

	@Override
	public void deleteEqCategory(List<Integer> ids) throws BusinessException {
		try {
			eqCategoryDao.deleteEqCategory(ids);
		} catch (Exception e) {
			throw new BusinessException("删除项目类别失败",e.getMessage());
		}
	}

	@Override
	public List<EquipmentCategory> serchEqCategory() throws BusinessException {
		try {
			return eqCategoryDao.serchEqCategory();
		} catch (Exception e) {
			throw new BusinessException("查询全部项目类别失败",e.getMessage());
		}
	}

}
