package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.StreamOriginDao;
import i9.defence.platform.dao.mapper.StreamOriginMapper;
import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.utils.PageBounds;

/**
 * DaoImpl
 * @author gbq
 * @create 2018年3月19日
 */
@Repository
public class StreamOriginDaoImpl implements StreamOriginDao {
	
	@Autowired
	private StreamOriginMapper streamOriginMapper;

	@Override
	public PageBounds<StreamOrigin> selectByLimitPage(StreamOriginSearchDto streamOriginSearchDto, int currectPage,
			int pageSize) throws Exception {
		final int totalSize = streamOriginMapper.countByExample(streamOriginSearchDto);
        PageBounds<StreamOrigin> pageBounds = new PageBounds<StreamOrigin>(currectPage, totalSize, pageSize);
        List<StreamOrigin> list = streamOriginMapper.selectByLimitPage(streamOriginSearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
	}

	@Override
	public void addStreamOrigin(StreamOrigin streamOrigin) throws Exception {
		streamOriginMapper.insertSelective(streamOrigin);
	}

}
