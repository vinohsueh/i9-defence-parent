package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.cache.ErrorTypeCache;
import i9.defence.platform.dao.EquipmentFaultDao;
import i9.defence.platform.dao.vo.EquipmentFaultSearchDto;
import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.service.EquipmentFaultService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月29日 上午11:35:00 
* 
*/
@Service
@Transactional
public class EquipmentFaultServiceImpl implements EquipmentFaultService{

	@Autowired
	private EquipmentFaultDao equipmentFaultDao;
	@Autowired
	private ErrorTypeCache errorTypeCache;
	@Override
	public PageBounds<EquipmentFault> selectByLimitPage(EquipmentFaultSearchDto equipmentFaultSearchDto) throws BusinessException {
        try {
            return equipmentFaultDao.selectByLimitPage(equipmentFaultSearchDto,equipmentFaultSearchDto.getCurrentPage(),equipmentFaultSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页查询失败",e.getMessage());
        }
    }

	@Override
	public void add(EquipmentFault equipmentFault) throws BusinessException {
		try {
			//id为null 则为添加
			EquipmentFault existEquipmentFault = equipmentFaultDao.getEquipmentId(equipmentFault.getCode(),equipmentFault.getEquipmentId());
			if(equipmentFault.getId() !=null) {
				if (existEquipmentFault != null && existEquipmentFault.getId() - equipmentFault.getId() !=0) {
		                throw new BusinessException("数据已存在!");
				}
				equipmentFaultDao.update(equipmentFault);
			}else {
			//id不为null则为更新
				if (existEquipmentFault != null  ) {
	                throw new BusinessException("数据已存在!");
				}
				equipmentFaultDao.add(equipmentFault);
			}
			errorTypeCache.init();
		}catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage());
		}catch (Exception e) {
            throw new BusinessException("添加失败",e.getMessage());
        }
	}

	@Override
	public void update(EquipmentFault equipmentFault) throws BusinessException {
		try {
			equipmentFaultDao.update(equipmentFault);
			errorTypeCache.init();
		}catch (Exception e) {
            throw new BusinessException("更新失败",e.getMessage());
        }
	}

	@Override
	public void deleteBatch(List<Integer> ids) throws BusinessException {
		try {
			equipmentFaultDao.deleteBatch(ids);
			errorTypeCache.init();
		}catch (Exception e) {
            throw new BusinessException("删除失败",e.getMessage());
        }
	}

	@Override
	public EquipmentFault getById(Integer id) throws BusinessException {
		try {
			EquipmentFault equipmentFault = equipmentFaultDao.getById(id);
			return equipmentFault;
		}catch (Exception e) {
            throw new BusinessException("查询失败",e.getMessage());
        }
	}

	@Override
	public List<EquipmentFault> getAllTypes() throws BusinessException {
		try {
			return equipmentFaultDao.getAllTypes();
		} catch (Exception e) {
			throw new BusinessException("查询失败",e.getMessage());
		}
	}
}
 