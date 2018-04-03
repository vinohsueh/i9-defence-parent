package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.HiddenDangerDao;
import i9.defence.platform.dao.mapper.HiddenDangerMapper;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;
import i9.defence.platform.utils.PageBounds;

@Repository
public class HiddenDangerDaoImpl implements HiddenDangerDao {

	@Autowired
	private HiddenDangerMapper hiddenDangerMapper;
	
	@Override
	public PageBounds<HiddenDanger> selectByLimitPage(HiddenDangerExample hiddenDangerExample, int currectPage,
			int pageSize) {
		final int totalSize = (int) hiddenDangerMapper.countByExample(hiddenDangerExample);
		PageBounds<HiddenDanger> pageBounds = new PageBounds<HiddenDanger>(currectPage, totalSize, pageSize); 
		List<HiddenDanger> list = hiddenDangerMapper.selectByLimitPage(hiddenDangerExample, pageBounds.getOffset(), pageBounds.getPageSize());
		pageBounds.setPageList(list); 
		return pageBounds;
	}

	@Override
	public void insertHiddenDanger(HiddenDanger hiddenDanger) throws Exception {
		hiddenDangerMapper.insert(hiddenDanger);
	}

}
