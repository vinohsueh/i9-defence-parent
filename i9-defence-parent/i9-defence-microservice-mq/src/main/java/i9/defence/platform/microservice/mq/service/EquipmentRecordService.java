package i9.defence.platform.microservice.mq.service;

/**
 * 设备记录服务类
 * 
 * @author R12
 * @date 2018年9月19日 13:02:31
 *
 */
public interface EquipmentRecordService {

    /**
     * 记录设备最后一次上行数据时间
     * 
     * @param deviceId
     */
    void recordLastDate(String deviceId);
}
