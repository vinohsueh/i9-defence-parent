package i9.defence.platform.datapush.service;

public interface OriginalRecordService {

    /**
     * 记录原始消息
     * 
     * @param message
     */
    void saveOriginalRecordMessage(String message);
}
