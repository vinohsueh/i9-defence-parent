package i9.defence.platform.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import i9.defence.platform.service.dto.DeviceError;
import i9.defence.platform.service.dto.DeviceErrorRowMapper;
import i9.defence.platform.service.dto.DeviceInfoDto;
import i9.defence.platform.service.dto.DeviceRowMapper;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.SqlUtil;

@Repository
public class ThirdPlatformRepositoryImpl implements ThirdPlatformRepository {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DeviceInfoDto selectEquipmentInfo(String deviceId) {
		try {
			List<DeviceInfoDto> list = jdbcTemplate.query(SqlUtil.EQUIPMENT_INFO_SQL, new DeviceRowMapper(), deviceId);
			if (list.size()>0) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<DeviceError> selectDeviceErrors() {
		try {
			List<DeviceError> list = jdbcTemplate.query(SqlUtil.EQUIPMENT_ERROR_SQL, new DeviceErrorRowMapper());
			return list;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Integer> selectUsefulChannel(String deviceId) {
		try {
			List<Integer> result = new ArrayList<Integer>();
			String sql = "select p.channel from t_passageway p where p.systemId = (select systemId from t_equipment where deviceId = '"+deviceId+"')";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					result.add((Integer) map.get("channel"));
				}
			}
			return result;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
