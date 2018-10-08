package i9.defence.platform.microservice.mq.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.microservice.mq.service.EquipmentRecordService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.StringUtil;

/**
 * 设备记录服务类
 * 
 * @author R12
 * @date 2018年9月19日 13:02:31
 *
 */
@Service
public class EquipmentRecordServiceImpl implements EquipmentRecordService {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 记录设备最后一次上行数据时间
     * 
     * @param deviceId
     */
    @Override
    public void recordLastDate(String deviceId) {
        try {
            equipmentService.updateEquipmentNewestTime(deviceId, StringUtil.dateToString(new Date()));
        } catch (Exception e) {
            throw new BusinessException("更新设备最新事件时间失败", e.getMessage());
        }
    }
}
