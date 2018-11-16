package i9.defence.platform.microservice.push.repository.impl;

import i9.defence.platform.microservice.push.repository.ThirdPlatformRepository;
import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceError;
import i9.defence.platform.microservice.push.vo.DeviceErrorRowMapper;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
import i9.defence.platform.microservice.push.vo.DeviceRowMapper;
import i9.defence.platform.microservice.push.vo.ProjectInfoDto;
import i9.defence.platform.microservice.push.vo.ProjectRowMapper;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.SqlUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class ThirdPlatformRepositoryImpl implements ThirdPlatformRepository {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPlatformRepositoryImpl.class);

    @Override
    public void createTableEveryday() throws SQLException {
        StringBuffer tableName = SqlUtil.getDeviceLogTableName();
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("CREATE TABLE `" + tableName + "` (");
            sql.append(SqlUtil.TABLE_SQL);
            jdbcTemplate.update(sql.toString());
            LOGGER.info("创建每日数据表成功, tableName : " + tableName);
        } catch (Exception e) {
            LOGGER.error("创建每日数据表失败, tableName : " + tableName, e);
        }
    }

    @Override
    public DeviceInfoDto selectEquipmentInfo(String deviceId) {
        try {
            List<DeviceInfoDto> list = jdbcTemplate.query(SqlUtil.EQUIPMENT_INFO_SQL, new DeviceRowMapper(), deviceId);
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public ProjectInfoDto selectProjectInfo(int id) {
        try {
            List<ProjectInfoDto> list = jdbcTemplate.query(SqlUtil.PROJECT_INFO_SQL, new ProjectRowMapper(), id);
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void insertIntoOldPlat(final DeviceInfoDto deviceInfoDto, final List<ChannelData> list) {
        try {
            jdbcTemplate.batchUpdate(SqlUtil.getInsertDeviceSql().toString(), new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, deviceInfoDto.getId());
                    ps.setTimestamp(2, new java.sql.Timestamp(list.get(i).getCreateTime().getTime()));
                    ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
                    String address = deviceInfoDto.getRemarks() + list.get(i).getLoop() + SqlUtil.LOOP_NAME
                            + list.get(i).getChannel() + SqlUtil.CHANNEL_NAME;
                    ps.setString(4, address);
                    ps.setString(5, SqlUtil.ERROR_TYPE);
                    ps.setString(6, list.get(i).getCode());
                }

                @Override
                public int getBatchSize() {
                    return list.size();
                }
            });
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
    public void updateDeviceStatus(final int id, final String status, final String alarm) {
        try {
            jdbcTemplate.update(SqlUtil.UPDATE_EQUIPMENT_SQL, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, status);
                    ps.setString(2, alarm);
                    ps.setInt(3, id);
                }
            });
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Integer> selectUsefulChannel(String deviceId) {
        try {
            List<Integer> result = new ArrayList<Integer>();
            String sql = "select p.channel from t_passageway p where p.systemId = (select systemId from t_equipment where deviceId = '"
                    + deviceId + "')";
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
