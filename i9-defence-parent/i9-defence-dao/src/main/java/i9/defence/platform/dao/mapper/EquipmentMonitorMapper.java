package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.EquipmentMonitor;
import i9.defence.platform.model.EquipmentMonitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentMonitorMapper {
    int countByExample(EquipmentMonitorExample example);

    int deleteByExample(EquipmentMonitorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentMonitor record);

    int insertSelective(EquipmentMonitor record);

    List<EquipmentMonitor> selectByExample(EquipmentMonitorExample example);

    EquipmentMonitor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentMonitor record, @Param("example") EquipmentMonitorExample example);

    int updateByExample(@Param("record") EquipmentMonitor record, @Param("example") EquipmentMonitorExample example);

    int updateByPrimaryKeySelective(EquipmentMonitor record);

    int updateByPrimaryKey(EquipmentMonitor record);
}