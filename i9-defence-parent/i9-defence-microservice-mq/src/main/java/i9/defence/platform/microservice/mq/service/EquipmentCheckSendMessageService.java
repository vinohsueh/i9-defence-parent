package i9.defence.platform.microservice.mq.service;

public interface EquipmentCheckSendMessageService {

    void checkEquipmentAndSendMessageAlarm(String deviceId, int alertStatus);

    void checkEquipmentAndSendMessageOffline(String deviceId);
}
