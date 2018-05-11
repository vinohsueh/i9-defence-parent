package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EqSystemCategoryDao;
import i9.defence.platform.dao.vo.EqSystemCategorySearchDto;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.service.EqSystemCategoryService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;
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

	@Override
	public PageBounds<EquipmentSystemtype> selectByLimitPage(EqSystemCategorySearchDto eqSystemCategorySearchDto)
			throws BusinessException {
		try {
			return eqSystemCategoryDao.selectByLimitPage(eqSystemCategorySearchDto, eqSystemCategorySearchDto.getCurrentPage(), eqSystemCategorySearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页查询项目类别失败",e.getMessage());
		}
	}

	@Override
	public void addEqSystemtype(EquipmentSystemtype equipmentSystemtype) throws BusinessException {
		try {
			if(equipmentSystemtype.getId()!=null) {
				eqSystemCategoryDao.updateEqSystemtype(equipmentSystemtype);
			}else {
				eqSystemCategoryDao.addEqSystemtype(equipmentSystemtype);
			}
		} catch (Exception e) {
			throw new BusinessException("添加项目类别失败",e.getMessage());
		}
	}


	@Override
	public EquipmentSystemtype getEqSystemtypeById(int id) throws BusinessException {
		try {
			return eqSystemCategoryDao.getEqSystemtypeById(id);
		} catch (Exception e) {
			throw new BusinessException("查询项目类别失败",e.getMessage());
		}
	}

	@Override
	public void deleteEqSystemtype(List<Integer> ids) throws BusinessException {
		try {
			eqSystemCategoryDao.deleteEqSystemtype(ids);
		} catch (Exception e) {
			throw new BusinessException("删除项目类别失败",e.getMessage());
		}
	}

}
