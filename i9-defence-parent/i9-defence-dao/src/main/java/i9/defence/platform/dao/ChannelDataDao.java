package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.model.ChannelData;

/** 
 * 通道数据
* 创建时间：2018年3月27日 下午1:45:03
* @author  lby
* @version  
* 
*/
public interface ChannelDataDao {
		
	/**
	 * 添加通道数据
	 * @param channelData
	 * @throws Exception
	 */
	void addChannelData(ChannelData channelData) throws Exception;
	
	/**
	 * 查询通道数据
	 * @param channelData
	 * @return
	 * @throws Exception
	 */
	List<ChannelData> selectChannelData(ChannelDataSearchDto channelDataSearchDto) throws Exception; 

	/**
     * 批量添加通道信息
     * @param records
     */
    void insertBatch(List<ChannelData> records) throws Exception; 
}
