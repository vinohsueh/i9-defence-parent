package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.EqSystemCategoryDao;
import i9.defence.platform.dao.mapper.EquipmentSystemtypeMapper;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.model.EquipmentSystemtypeExample;
/**
 * DaoImpl
 * @author gbq
 * @create 
 */
@Repository
public class EqSystemCategoryDaoImpl implements EqSystemCategoryDao{
	
	@Autowired
	private EquipmentSystemtypeMapper equipmentSystemtypeMapper;

	@Override
	public List<EquipmentSystemtype> findEquipmentSystemCategory() throws Exception {
		EquipmentSystemtypeExample example=new EquipmentSystemtypeExample();
		return equipmentSystemtypeMapper.selectByExample(example);
	}

}
