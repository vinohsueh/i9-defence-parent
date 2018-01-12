package i9.defence.platform.dao.impl;

import i9.defence.platform.dao.EquipmentFailureDao;
import i9.defence.platform.dao.mapper.EquipmentFailureMapper;
import i9.defence.platform.model.EquipmentFailure;
import i9.defence.platform.model.EquipmentFailureExample;
import i9.defence.platform.utils.PageBounds;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 设备故障Impl
* @ClassName: EquipmentFailureDaoImpl 
* @Description: TODO
* @author luobo
* @date 2018年1月8日 下午5:22:19 
*
 */
@Repository
public class EquipmentFailureDaoImpl implements EquipmentFailureDao {
	
	@Autowired
	private EquipmentFailureMapper equipmentFailureMapper;
	
	/**
	 * 增加故障设备
	 */
	@Override
	public void addEquipmentFailure(EquipmentFailure equipmentFailure) {
		equipmentFailureMapper.insert(equipmentFailure);
	}

	/**
	 * 删除故障设备
	 */
	@Override
	public void deleteEquipmentFailure(List<Integer> ids) throws Exception {
		 equipmentFailureMapper.deleteByPrimaryKey(ids);
	} 

	/**
	 * 更新故障设备
	 */
	@Override
	public void updateEquipmentFailure(EquipmentFailure equipmentFailure)
			throws Exception {
		equipmentFailureMapper.updateByPrimaryKeySelective(equipmentFailure);
		
	}

	/**
	 * 根据Id查询故障设备
	 */
	@Override
	public EquipmentFailure getEquipmentFailureById(int id) throws Exception {
		return equipmentFailureMapper.selectByPrimaryKey(id); 
	}

	/**
	 * 分页查询故障设备
	 */
	@Override
	public PageBounds<EquipmentFailure> selectByLimitPage(
			EquipmentFailureExample equipmentFailureExample, int currectPage,
			int pageSize) throws Exception {
			final	int totalSize = equipmentFailureMapper.countByExample(equipmentFailureExample); 
			PageBounds<EquipmentFailure> pageBounds = new PageBounds<EquipmentFailure>(currectPage, totalSize, pageSize);
			List<EquipmentFailure> list = equipmentFailureMapper.selectByLimitPage(equipmentFailureExample, pageBounds.getOffset(), pageBounds.getPageSize());
			pageBounds.setPageList(list);
		return pageBounds;
	}
	

}
