package i9.defence.platform.dao.impl;

import i9.defence.platform.dao.HiddenDangerInfoDao;
import i9.defence.platform.dao.mapper.HiddenDangerInfoMapper;
import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.model.HiddenDangerInfoExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 隐患提醒DaoImpl
* @ClassName: HiddenDangerInfoDaoImpl 
* @Description: TODO
* @author luobo
* @date 2018年1月9日 下午4:45:58 
*
 */
@Repository
public class HiddenDangerInfoDaoImpl implements HiddenDangerInfoDao {
	
	@Autowired
	private HiddenDangerInfoMapper hiddenDangerInfoMapper;
	/**
	 * 增加隐患类型
	 */
	@Override
	public void addHiddenDangerInfo(HiddenDangerInfo hiddenDangerInfo) {
			hiddenDangerInfoMapper.insert(hiddenDangerInfo);
	}
	
	/**
	 * 分页查询
	 * @param <T>
	 */
	@Override
	public PageBounds<HiddenDangerInfo> selectByLimitPage(
			HiddenDangerInfoExample hiddenDangerInfoExample, int currectPage,
			int pageSize) throws Exception {
		final	int totalSize = hiddenDangerInfoMapper.countByExample(hiddenDangerInfoExample); 
		PageBounds<HiddenDangerInfo> pageBounds = new PageBounds<HiddenDangerInfo>(currectPage, totalSize, pageSize);
		List<HiddenDangerInfo> list = hiddenDangerInfoMapper.selectByLimitPage(hiddenDangerInfoExample, pageBounds.getOffset(),pageBounds.getPageSize());
		pageBounds.setPageList(list);
		return pageBounds;
	}
	
	/**
	 * 根据id查询
	 */
	@Override
	public HiddenDangerInfo selectById(int id) throws Exception {
		return hiddenDangerInfoMapper.selectByPrimaryKey(id);
	}

 

}
