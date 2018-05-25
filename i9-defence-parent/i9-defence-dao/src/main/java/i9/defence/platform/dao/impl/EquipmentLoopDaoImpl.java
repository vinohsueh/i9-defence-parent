package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.EquipmentLoopDao;
import i9.defence.platform.dao.mapper.EquipmentLoopMapper;
import i9.defence.platform.dao.vo.EquipmentLoopDto;
import i9.defence.platform.model.EquipmentLoop;
import i9.defence.platform.utils.PageBounds;

/**
 * 设备回路表daoImpl
 * @ClassName: EquipmentLoopDao 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年5月25日 下午4:32:20
 */
@Repository
public class EquipmentLoopDaoImpl implements EquipmentLoopDao {

	@Autowired
	private EquipmentLoopMapper equipmentLoopMapper;
	
	@Override
	public PageBounds<EquipmentLoop> selectByLimitPage(EquipmentLoopDto equipmentLoopDto, int currectPage, int pageSize)
			throws Exception {
		final int  totalSize = (int) equipmentLoopMapper.countByExample(equipmentLoopDto);
		PageBounds<EquipmentLoop> pageBounds = new PageBounds<EquipmentLoop>(currectPage, totalSize, pageSize);
		List<EquipmentLoop> list = equipmentLoopMapper.selectByLimitPage(equipmentLoopDto, pageBounds.getOffset(), pageBounds.getPageSize());
		pageBounds.setPageList(list);
		return pageBounds;
	}

	@Override
	public void insertEquipLoop(EquipmentLoop equipmentLoop) throws Exception {
		equipmentLoopMapper.insert(equipmentLoop);
	}

}
