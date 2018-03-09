package i9.defence.platform.dao.impl;

import i9.defence.platform.dao.HiddenDangerDao;
import i9.defence.platform.dao.mapper.HiddenDangerMapper;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 隐患提醒类型Impl
* @ClassName: HiddenDangerDaoImpl 
* @Description: TODO
* @author luobo
* @date 2018年1月10日 上午10:46:34 
*
 */
@Repository
public class HiddenDangerDaoImpl implements HiddenDangerDao {

	@Autowired
	private HiddenDangerMapper hiddenDangerMapper;
	/**
	 * 增加隐患提醒类型
	 */
	@Override
	public void addHiddenDanger(HiddenDanger hiddenDanger) throws Exception {
			hiddenDangerMapper.insertSelective(hiddenDanger);
	}
	
	/**
	 * 分页查询隐患提醒类型
	 */
	@Override
	public PageBounds<HiddenDanger> selectByLimitPage( 
			HiddenDangerDto hiddenDangerDto, int currectPage, int pageSize)
			throws Exception {
	   final int totalSize = hiddenDangerMapper.countByExample(hiddenDangerDto); 
	   PageBounds<HiddenDanger> pageBounds = new PageBounds<HiddenDanger>(currectPage, totalSize, pageSize);
	   List<HiddenDanger> list = hiddenDangerMapper.selectByLimitPage(hiddenDangerDto, pageBounds.getOffset(), pageBounds.getPageSize());
	   pageBounds.setPageList(list); 
	   return pageBounds; 
	}
	
	/**
	 * 根据id查询
	 */
	@Override
	public HiddenDanger getHiddenDanger(int id) throws Exception {
		return hiddenDangerMapper.selectByPrimaryKey(id); 
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateHiddenDanger(HiddenDanger hiddenDanger) throws Exception {
		hiddenDangerMapper.updateByPrimaryKey(hiddenDanger);
	}
	
	/**
	 * 获取所有
	 */
	@Override
	public List<HiddenDanger> getAllHidenDangers() throws Exception {
		HiddenDangerExample example = new HiddenDangerExample();
		List<HiddenDanger> list = hiddenDangerMapper.selectByExample(example);
		return list;
	}
	
 
}
