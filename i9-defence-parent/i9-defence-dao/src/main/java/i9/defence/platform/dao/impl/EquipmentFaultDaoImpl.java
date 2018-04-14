package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.EquipmentFaultDao;
import i9.defence.platform.dao.mapper.EquipmentFaultMapper;
import i9.defence.platform.dao.vo.EquipmentFaultSearchDto;
import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.model.EquipmentFaultExample;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月29日 上午11:29:25 
* 
*/
@Repository
public class EquipmentFaultDaoImpl implements EquipmentFaultDao{

	@Autowired
	private EquipmentFaultMapper equipmentFaultMapper;
	@Override
	public PageBounds<EquipmentFault> selectByLimitPage(EquipmentFaultSearchDto equipmentFaultSearchDto,
			int currectPage, int pageSize) throws Exception {
        final int totalSize = (int) equipmentFaultMapper.countByExample(equipmentFaultSearchDto);
        PageBounds<EquipmentFault> pageBounds = new PageBounds<EquipmentFault>(currectPage, totalSize, pageSize);
        List<EquipmentFault> list = equipmentFaultMapper.selectByLimitPage(equipmentFaultSearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

	@Override
	public void add(EquipmentFault equipmentFault) throws Exception {
		equipmentFaultMapper.insertSelective(equipmentFault);
	}

	@Override
	public void update(EquipmentFault equipmentFault) throws Exception {
		equipmentFaultMapper.updateByPrimaryKeySelective(equipmentFault);
	}

	@Override
	public void deleteBatch(List<Integer> ids) throws Exception {
		equipmentFaultMapper.deleteByPrimaryKey(ids);
	}

	@Override
	public EquipmentFault getById(Integer id) throws Exception {
		EquipmentFault equipmentFault= equipmentFaultMapper.selectByPrimaryKey(id);
		return equipmentFault;
	}

	@Override
	public List<EquipmentFault> getAllTypes() throws Exception {
		EquipmentFaultExample example = new EquipmentFaultExample();
		return equipmentFaultMapper.selectByExample(example);
	}
}
 