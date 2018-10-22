package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceDataHis;
import i9.defence.platform.datapush.respository.DeviceDataHisRepository;
import i9.defence.platform.datapush.service.DeviceDataHisService;
import i9.defence.platform.datapush.utils.DateUtil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataHisServiceImpl implements DeviceDataHisService {

    @Autowired
    private DeviceDataHisRepository deviceDataHisRepository;

    @Override
    public List<DeviceDataHis> queryDeviceDataHisDto(String deviceId, String datastream, String startDate,
            String endDate) throws Exception {
        Date sDate = DateUtil.parse(startDate);
        Date eDate = DateUtil.parse(endDate);
        List<DeviceDataHis> deviceDataHis = this.deviceDataHisRepository.queryDeviceDataHisDto(deviceId, datastream,
                sDate, eDate);
        return deviceDataHis;
    }
}
