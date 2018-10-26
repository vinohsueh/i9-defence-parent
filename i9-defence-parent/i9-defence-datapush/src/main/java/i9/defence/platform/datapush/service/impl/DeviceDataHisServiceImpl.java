package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceDataHis;
import i9.defence.platform.datapush.respository.DeviceDataHisRepository;
import i9.defence.platform.datapush.service.DeviceDataHisService;
import i9.defence.platform.datapush.utils.DateUtil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 设备数据历史记录服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:42:15
 */
@Service
public class DeviceDataHisServiceImpl implements DeviceDataHisService {

    @Autowired
    private DeviceDataHisRepository deviceDataHisRepository;

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
    @Override
    public List<DeviceDataHis> queryDeviceDataHisDto(String deviceId, String datastream, String startDate,
            String endDate) throws Exception {
        Date sDate = DateUtil.parse(startDate);
        Date eDate = DateUtil.parse(endDate);
        List<DeviceDataHis> deviceDataHis = this.deviceDataHisRepository.queryDeviceDataHisDto(deviceId, datastream,
                sDate, eDate);
        return deviceDataHis;
    }

    /**
     * 保存设备数据历史记录
     * 
     * @param deviceDataHis
     */
    @Override
    public void saveDeviceDataHis(DeviceDataHis deviceDataHis) {
        this.deviceDataHisRepository.save(deviceDataHis);
    }
}
