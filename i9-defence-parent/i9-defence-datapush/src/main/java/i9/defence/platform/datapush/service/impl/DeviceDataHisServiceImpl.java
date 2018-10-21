package i9.defence.platform.datapush.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.datapush.entity.DeviceDataHis;
import i9.defence.platform.datapush.respository.DeviceDataHisRepository;
import i9.defence.platform.datapush.service.DeviceDataHisService;

@Service
public class DeviceDataHisServiceImpl implements DeviceDataHisService {

    @Autowired
    private DeviceDataHisRepository deviceDataHisRepository;

    @Override
    public List<DeviceDataHis> queryDeviceDataHisDto(String deviceId, String datastream, String startDate,
            String endDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sDate = dateFormat.parse(startDate);
        Date eDate = dateFormat.parse(endDate);
        List<DeviceDataHis> deviceDataHis = this.deviceDataHisRepository.queryDeviceDataHisDto(deviceId, datastream,
                sDate, eDate);
        return deviceDataHis;
    }
}
