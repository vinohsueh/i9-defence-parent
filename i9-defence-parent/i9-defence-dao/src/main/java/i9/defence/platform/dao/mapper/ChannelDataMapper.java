package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.ChannelDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelDataMapper {
    long countByExample(ChannelDataExample example);

    int deleteByExample(ChannelDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChannelData record);

    int insertSelective(ChannelData record);

    List<ChannelData> selectByExample(ChannelDataExample example);

    ChannelData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChannelData record, @Param("example") ChannelDataExample example);

    int updateByExample(@Param("record") ChannelData record, @Param("example") ChannelDataExample example);

    int updateByPrimaryKeySelective(ChannelData record);

    int updateByPrimaryKey(ChannelData record);
    
    /**
     * 批量添加通道信息
     * @param records
     */
    void insertBatch(List<ChannelData> records);
    
    /**
     * 条件查询
     * @param channelData
     * @return
     */
	List<ChannelData> selectChannelData(ChannelDataSearchDto channelDataSearchDto);
	
	/**
	 * 批量修改标识
	 */
	void updateDealStatusByIds(@Param("example") DealStatusDto dealStatusDto);
}