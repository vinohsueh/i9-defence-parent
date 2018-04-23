package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.EquipmentCategoryDao;
import i9.defence.platform.dao.mapper.EquipmentCategoryMapper;
import i9.defence.platform.dao.vo.EqCategorySearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.EquipmentCategoryExample;
import i9.defence.platform.utils.PageBounds;

/**
 * 项目DaoImpl
 * @author gbq
 * @create 
 */
@Repository
public class EquipmentCategoryDaoImpl implements EquipmentCategoryDao {

	@Autowired
	private EquipmentCategoryMapper eqCategoryMapper;
	
	@Override
	public PageBounds<EquipmentCategory> selectByLimitPage(EqCategorySearchDto eqCategorySearchDto, int currectPage,
			int pageSize) throws Exception {
		final int totalSize = eqCategoryMapper.countByExample(eqCategorySearchDto);
        PageBounds<EquipmentCategory> pageBounds = new PageBounds<EquipmentCategory>(currectPage, totalSize, pageSize);
        List<EquipmentCategory> list = eqCategoryMapper.selectByLimitPage(eqCategorySearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
	}

	@Override
	public void addEqCategory(EquipmentCategory eqCategory) throws Exception {
		eqCategoryMapper.insertSelective(eqCategory);
	}

	@Override
	public void updateEqCategory(EquipmentCategory eqCategory) throws Exception {
		eqCategoryMapper.updateByPrimaryKeySelective(eqCategory);
	}

	@Override
	public EquipmentCategory getEqCategoryById(int id) throws Exception {
		return eqCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteEqCategory(List<Integer> ids) throws Exception {
		eqCategoryMapper.deleteByPrimaryKey(ids);		
	}

	@Override
	public List<EquipmentCategory> serchEqCategory() throws Exception {
		EquipmentCategoryExample example = new EquipmentCategoryExample();
		return eqCategoryMapper.selectByExample(example);
	}

	@Override
	public List<EquipmentCategory> selectAllEqCategoryAndNum(EquipmentCategory equipmentCategory) throws Exception {
		return eqCategoryMapper.selectAllEqCategoryAndNum(equipmentCategory);
	}

	/*@Override
	public List<EquipmentCategory> selectAllEqCategoryAndNum1(EquipmentCategory equipmentCategory) throws Exception {
		return eqCategoryMapper.selectAllEqCategoryAndNum1(equipmentCategory);
	}*/

}
