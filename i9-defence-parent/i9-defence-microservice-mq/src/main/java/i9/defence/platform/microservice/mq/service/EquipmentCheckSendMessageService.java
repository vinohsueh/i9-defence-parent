package i9.defence.platform.microservice.mq.service;

public interface EquipmentCheckSendMessageService {

    void checkEquipmentAndSendMessageAlarm(String deviceId, int alertStatus, String jsonStr);

    void checkEquipmentAndSendMessageOffline(String deviceId);

}
