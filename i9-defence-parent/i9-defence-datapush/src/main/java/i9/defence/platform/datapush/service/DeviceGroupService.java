package i9.defence.platform.datapush.service;

import java.util.HashSet;
import java.util.List;

import i9.defence.platform.datapush.entity.DeviceGroupInfo;

public interface DeviceGroupService {

    /**
     * 查询设备组信息
     * 
     * @param idList
     * @return
     */
    List<DeviceGroupInfo> getDeviceGroupInfoByIdList(HashSet<String> idList);

}
