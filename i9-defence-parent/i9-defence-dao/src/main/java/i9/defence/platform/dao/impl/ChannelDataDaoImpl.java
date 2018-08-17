package i9.defence.platform.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ChannelDataDao;
import i9.defence.platform.dao.mapper.ChannelDataMapper;
import i9.defence.platform.dao.vo.ChannelDataLimitPageDto;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;

/** 
* 创建时间：2018年3月27日 下午2:44:22
* @author  lby
* @version  
* 
*/
@Repository
public class ChannelDataDaoImpl implements ChannelDataDao{
	
	@Autowired
	private ChannelDataMapper channelDataMapper;
	
	@Override
	public void addChannelData(ChannelData channelData) throws Exception {
		channelDataMapper.insertSelective(channelData);
	}

	@Override
	public List<ChannelData> selectChannelData(ChannelDataSearchDto channelDataSearchDto) throws Exception {
		return channelDataMapper.selectChannelData(channelDataSearchDto);
	}

	@Override
	public void insertBatch(List<ChannelData> records) throws Exception {
		channelDataMapper.insertBatch(records);
	}

	@Override
	public PageBounds<ChannelDataLimitPageDto> selectByLimitPage(ChannelDataSearchDto channelDataSearchDto, int currectPage, int pageSize)
			throws Exception {
		channelDataSearchDto.setTypes(Arrays.asList(Constants.S_Chaanel_TYPES));
		final int totalSize = channelDataMapper.countByExample(channelDataSearchDto);
		PageBounds<ChannelDataLimitPageDto> pageBounds = new PageBounds<ChannelDataLimitPageDto>(currectPage,totalSize, pageSize);
		List<ChannelDataLimitPageDto> list = channelDataMapper.selectByLimitPage(channelDataSearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
		pageBounds.setPageList(list);
		return pageBounds;
	}

	@Override
	public void insertEquipmentRecents(List<ChannelData> channelDatas) throws Exception {
		channelDataMapper.insertEquipmentRecents(channelDatas);
	}
	
	
	
}
