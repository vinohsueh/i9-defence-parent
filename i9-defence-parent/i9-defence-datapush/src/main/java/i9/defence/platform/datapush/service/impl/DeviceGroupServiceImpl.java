package i9.defence.platform.datapush.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.datapush.entity.DeviceGroupInfo;
import i9.defence.platform.datapush.respository.DeviceGroupInfoRepository;
import i9.defence.platform.datapush.service.DeviceGroupService;

@Service
public class DeviceGroupServiceImpl implements DeviceGroupService {
    
    @Autowired
    private DeviceGroupInfoRepository deviceGroupInfoRepository;

    /**
     * 查询设备组信息
     * 
     * @param idList
     * @return
     */
    @Override
    public List<DeviceGroupInfo> getDeviceGroupInfoByIdList(HashSet<String> idList) {
        if (idList.isEmpty()) {
            return new ArrayList<DeviceGroupInfo>();
        }
        List<String> idList0 = new ArrayList<String>(idList);
        List<DeviceGroupInfo> list = this.deviceGroupInfoRepository.queryDeviceGroupInfoByIdList(idList0);
        return list;
    }

}
