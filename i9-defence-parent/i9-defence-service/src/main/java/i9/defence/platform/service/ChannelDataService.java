package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.ChannelDataLimitPageDto;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
* 创建时间：2018年3月27日 下午2:54:15
* @author  lby
* @version  
* 
*/
public interface ChannelDataService {
	
	/**
	 * 添加通道数据
	 * @param channelData
	 * @throws BusinessException
	 */
	void addChannelData(ChannelData channelData) throws BusinessException;
	
	/**
	 * 查询通道数据
	 * @param channelData
	 * @return
	 * @throws BusinessException
	 */
	List<ChannelData> selectChannelData(ChannelDataSearchDto channelDataSearchDto) throws BusinessException; 

	   /**
     * 分页查询
    * @Title: selectByLimitPage 
    * @Description: TODO
    * @param managerSearchDto
    * @param currectPage
    * @param pageSize
    * @return
    * @throws Exception
     */
    PageBounds<ChannelDataLimitPageDto> selectByLimitPage( ChannelDataSearchDto channelDataSearchDto,  int currectPage,  int pageSize)throws  BusinessException;

}
