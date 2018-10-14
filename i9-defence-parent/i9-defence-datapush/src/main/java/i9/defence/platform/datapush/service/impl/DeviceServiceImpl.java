package i9.defence.platform.datapush.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.respository.DeviceInfoRepository;
import i9.defence.platform.datapush.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    public List<DeviceInfo> getDeviceInfoList() {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        List<DeviceInfo> List = this.deviceInfoRepository.findAll(sort);
        return List;
    }

    @Override
    public DeviceInfo getDeviceInfoById(String id) {
        DeviceInfo deviceInfo = this.deviceInfoRepository.findOne(id);
        return deviceInfo;
    }

    @Transactional
    @Override
    public void deleteDevice(String id) {
        this.deviceInfoRepository.delete(id);
    }
}
