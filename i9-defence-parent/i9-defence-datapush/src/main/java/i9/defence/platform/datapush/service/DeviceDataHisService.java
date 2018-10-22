package i9.defence.platform.datapush.service;

import java.util.List;

import i9.defence.platform.datapush.entity.DeviceDataHis;

/**
 * 设备数据历史记录服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:42:15
 */
public interface DeviceDataHisService {

    /**
     * 查询设备数据历史记录列表
     * 
     * @param deviceId
     * @param datastream
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    List<DeviceDataHis> queryDeviceDataHisDto(String deviceId, String datastream, String startDate, String endDate)
            throws Exception;
}
