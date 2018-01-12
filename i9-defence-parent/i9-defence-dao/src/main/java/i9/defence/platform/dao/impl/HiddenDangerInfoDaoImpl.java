package i9.defence.platform.dao.impl;

import java.util.List;

import i9.defence.platform.dao.HiddenDangerInfoDao;
import i9.defence.platform.dao.mapper.HiddenDangerInfoMapper;
import i9.defence.platform.dao.vo.HiddenDangerInfoDto;
import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.utils.PageBounds;

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
			HiddenDangerInfoDto hiddenDangerInfoInfoDto, int currectPage,
			int pageSize) throws Exception {
		final	int totalSize = hiddenDangerInfoMapper.countByExample(hiddenDangerInfoInfoDto);
		PageBounds<HiddenDangerInfo> pageBounds = new PageBounds<HiddenDangerInfo>(currectPage, totalSize, pageSize);
		List<HiddenDangerInfo> list = hiddenDangerInfoMapper.selectByLimitPage(hiddenDangerInfoInfoDto, pageBounds.getOffset(),pageBounds.getPageSize());
		pageBounds.setPageList(list);
		return pageBounds;
	}

}
