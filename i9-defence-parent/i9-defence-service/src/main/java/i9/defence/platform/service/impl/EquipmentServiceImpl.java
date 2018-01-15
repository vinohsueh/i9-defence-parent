package i9.defence.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;
/**
 * 项目类别ServiceImpl
 * @author gbq
 * @create 2018年
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Override
	public PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto)
			throws BusinessException {
		try {
			return equipmentDao.selectByLimitPage(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页项目类别类别查询失败",e.getMessage());
		}
	}

	@Override
	public void addEquipment(Equipment equipment) throws BusinessException {
		try {
			if(equipment.getId()!=null) {
				equipmentDao.updateEquipment(equipment);
			}else {
				equipment.setEquipmentDate(new Date());
				equipmentDao.addEquipment(equipment);
			}
		} catch (Exception e) {
			throw new BusinessException("添加项目类别类别失败",e.getMessage());
		}
	}

	@Override
	public void updateEquipment(Equipment equipment) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			equipmentDao.updateEquipment(equipment);
		} catch (Exception e) {
			throw new BusinessException("更新项目类别失败",e.getMessage());
		}
	}

	@Override
	public Equipment getEquipmentById(int id) throws BusinessException {
		try {
			return equipmentDao.getEquipmentById(id);
		} catch (Exception e) {
			throw new BusinessException("查询项目类别失败",e.getMessage());
		}
	}

	@Override
	public void deleteEquipment(List<Integer> ids) throws BusinessException {
		try {
			equipmentDao.deleteEquipment(ids);
		} catch (Exception e) {
			throw new BusinessException("删除项目类别失败",e.getMessage());
		}
	}

}
