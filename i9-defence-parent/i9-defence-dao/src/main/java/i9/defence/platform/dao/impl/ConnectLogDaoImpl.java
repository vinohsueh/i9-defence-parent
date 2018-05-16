package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ConnectLogDao;
import i9.defence.platform.dao.mapper.ConnectLogMapper;
import i9.defence.platform.dao.vo.ConnectLogDto;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.utils.PageBounds;

/** 
* 创建时间：2018年5月11日 下午5:01:59
* @author  lby
* @version  
* 
*/
@Repository
public class ConnectLogDaoImpl implements ConnectLogDao{

	@Autowired
	private ConnectLogMapper connectLogMapper;
	
	@Override
	public void add(ConnectLog connectLog) {
		connectLogMapper.insertSelective(connectLog);
	}

	@Override
	public PageBounds<ConnectLogDto> selectByLimitPage(ConnectLogDto connectLogDto, int currentPage, int pageSize) {
		final int totalSize = connectLogMapper.countByExample(connectLogDto);
        PageBounds<ConnectLogDto> pageBounds = new PageBounds<ConnectLogDto>(currentPage, totalSize, pageSize);
        List<ConnectLogDto> list = connectLogMapper.selectByLimitPage(connectLogDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
	}

}
