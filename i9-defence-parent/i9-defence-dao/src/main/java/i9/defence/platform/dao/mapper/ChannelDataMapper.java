package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.ChannelDataLimitPageDto;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.ChannelDataExample;

import java.util.Date;

public interface ChannelDataMapper {
    int countByExample(@Param("example") ChannelDataSearchDto channelDataSearchDto); 

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
    
    List<ChannelDataLimitPageDto> selectByLimitPage(@Param("example") ChannelDataSearchDto channelDataSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
    
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
	void updateDealStatusByIds(@Param("list") List<DealStatusDto> list,@Param("managerId") Integer managerId,@Param("nowDate") Date nowDate);

	/**
	 * 批量新增/更新近期数据表
	* @Title: insertEquipmentRecents 
	* @Description: TODO
	* @param equipmentRecents
	 */
	void insertEquipmentRecents(@Param("channelDatas") List<ChannelData> channelDatas); 
}