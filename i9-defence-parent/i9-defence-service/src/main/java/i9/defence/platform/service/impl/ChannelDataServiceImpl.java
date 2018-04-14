package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ChannelDataDao;
import i9.defence.platform.dao.vo.ChannelDataLimitPageDto;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.service.ChannelDataService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
* 创建时间：2018年3月27日 下午2:54:44
* @author  lby
* @version  
* 
*/
@Service
@Transactional
public class ChannelDataServiceImpl implements ChannelDataService{
	
	@Autowired
	private ChannelDataDao channelDataDao;
	
	@Override
	public void addChannelData(ChannelData channelData) throws BusinessException {
		try {
			channelDataDao.addChannelData(channelData);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ChannelData> selectChannelData(ChannelDataSearchDto channelDataSearchDto) throws BusinessException {
		try {
			return channelDataDao.selectChannelData(channelDataSearchDto);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public PageBounds<ChannelDataLimitPageDto> selectByLimitPage(ChannelDataSearchDto channelDataSearchDto, int currectPage,
			int pageSize) throws BusinessException {
		try {
			return channelDataDao.selectByLimitPage(channelDataSearchDto, currectPage, pageSize);
		} catch (Exception e) {
			  throw new BusinessException("分页查询channel失败",e.getMessage());		}
	}
	
}
