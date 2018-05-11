package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.EqSystemCategoryDao;
import i9.defence.platform.dao.mapper.EquipmentSystemtypeMapper;
import i9.defence.platform.dao.vo.EqSystemCategorySearchDto;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.model.EquipmentSystemtypeExample;
import i9.defence.platform.utils.PageBounds;
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

	@Override
	public PageBounds<EquipmentSystemtype> selectByLimitPage(EqSystemCategorySearchDto eqSystemCategorySearchDto,
			int currentPage, int pageSize) throws Exception {
		final int totalSize = equipmentSystemtypeMapper.countByExample(eqSystemCategorySearchDto);
        PageBounds<EquipmentSystemtype> pageBounds = new PageBounds<EquipmentSystemtype>(currentPage, totalSize, pageSize);
        List<EquipmentSystemtype> list = equipmentSystemtypeMapper.selectByLimitPage(eqSystemCategorySearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
	}

	@Override
	public void addEqSystemtype(EquipmentSystemtype equipmentSystemtype) throws Exception {
		equipmentSystemtypeMapper.insertSelective(equipmentSystemtype);
	}

	@Override
	public void updateEqSystemtype(EquipmentSystemtype equipmentSystemtype) throws Exception {
		equipmentSystemtypeMapper.updateByPrimaryKeySelective(equipmentSystemtype);	
	}

	@Override
	public EquipmentSystemtype getEqSystemtypeById(int id) throws Exception {
		return equipmentSystemtypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteEqSystemtype(List<Integer> ids) throws Exception {
		equipmentSystemtypeMapper.deleteByPrimaryKey(ids);
	}

}
