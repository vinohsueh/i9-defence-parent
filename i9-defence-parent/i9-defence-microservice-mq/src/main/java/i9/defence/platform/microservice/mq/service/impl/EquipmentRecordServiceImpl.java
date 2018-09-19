package i9.defence.platform.microservice.mq.service.impl;

import org.springframework.stereotype.Service;

import i9.defence.platform.microservice.mq.service.EquipmentRecordService;

/**
 * 设备记录服务类
 * 
 * @author R12
 * @date 2018年9月19日 13:02:31
 *
 */
@Service
public class EquipmentRecordServiceImpl implements EquipmentRecordService {

    /**
     * 记录设备最后一次上行数据时间
     * 
     * @param deviceId
     */
    @Override
    public void recordLastDate(String deviceId) {
    }
}
