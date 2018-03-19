package i9.defence.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.StreamOriginDao;
import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.service.StreamOriginService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;
@Service
@Transactional
public class StreamOriginServiceImpl implements StreamOriginService {

	@Autowired
	private StreamOriginDao streamOriginDao;
	
	@Override
	public PageBounds<StreamOrigin> selectByLimitPage(StreamOriginSearchDto streamOriginSearchDto)
			throws BusinessException {
		try {
			return streamOriginDao.selectByLimitPage(streamOriginSearchDto, streamOriginSearchDto.getCurrentPage(), streamOriginSearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页项目类别类别查询失败",e.getMessage());
		}
	}

	@Override
	public void addStreamOrigin(StreamOrigin streamOrigin) throws BusinessException {
		try {
			streamOriginDao.addStreamOrigin(streamOrigin);
		} catch (Exception e) {
			throw new BusinessException("添加原始数据失败",e.getMessage());
		}
		
	}

}
