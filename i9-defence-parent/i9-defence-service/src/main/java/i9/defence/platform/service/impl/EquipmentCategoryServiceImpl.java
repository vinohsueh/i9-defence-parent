package i9.defence.platform.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EquipmentCategoryDao;
import i9.defence.platform.dao.vo.EqCategorySearchDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
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
	@Autowired
	private ManagerService managerService;
	
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
			Manager loginManager = managerService.getLoginManager();
			EquipmentCategory equipmentCategory = new EquipmentCategory();
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				return eqCategoryDao.selectAllEqCategoryAndNum(equipmentCategory);
			}
			//如果为经销商和管理员
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				equipmentCategory.setDistributorId(loginManager.getId());
				return eqCategoryDao.selectAllEqCategoryAndNum(equipmentCategory);
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员
				equipmentCategory.setPrijrctManagerId(loginManager.getId());
				return eqCategoryDao.selectAllEqCategoryAndNum1(equipmentCategory);
			}
		} catch (Exception e) {
			throw new BusinessException("查询全部项目类别失败",e.getMessage());
		}
		return null;
	}

//	@Override
//	public List<EqCategorySearchDto> selectAllEqCategoryAndNum() throws BusinessException {
//		try {
//			Manager loginManager = managerService.getLoginManager();
//			EqCategorySearchDto eqCategorySearchDto = new EqCategorySearchDto();
//			//如果为网站用户显示全部（type=0）
//			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
//				return eqCategoryDao.selectAllEqCategoryAndNum(eqCategorySearchDto);
//			}
//			//如果为经销商和管理员
//			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
//				eqCategorySearchDto.setDistributorId(loginManager.getId());
//				return eqCategoryDao.selectAllEqCategoryAndNum(eqCategorySearchDto);
//			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
//				//如果是项目管理员
//				eqCategorySearchDto.setPrijrctManagerId(loginManager.getId());
//				return eqCategoryDao.selectAllEqCategoryAndNum(eqCategorySearchDto);
//			}
//		} catch (Exception e) {
//			throw new BusinessException("查询全部项目类别失败",e.getMessage());
//		}
//		return null;
//	}

}
