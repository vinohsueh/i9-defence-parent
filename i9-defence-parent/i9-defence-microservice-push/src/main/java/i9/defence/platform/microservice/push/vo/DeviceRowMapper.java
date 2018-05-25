package i9.defence.platform.microservice.push.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/** 
* 创建时间：2018年5月22日 上午11:39:14
* @author  lby
* @version  
* 
*/
public class DeviceRowMapper implements RowMapper<DeviceInfoDto>{

    @Override
    public DeviceInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
    	DeviceInfoDto device = new DeviceInfoDto();
        device.setId(resultSet.getInt("id"));
        device.setRemarks(resultSet.getString("equipmentRemarks"));
        device.setEquipmentId(resultSet.getInt("equipmentCategoryId"));
        return device;
    }

}
