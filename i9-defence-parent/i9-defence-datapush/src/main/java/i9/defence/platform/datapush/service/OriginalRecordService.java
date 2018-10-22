package i9.defence.platform.datapush.service;

/**
 * 原始消息服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:51:12
 */
public interface OriginalRecordService {

    /**
     * 记录原始消息
     * 
     * @param message
     */
    void saveOriginalRecordMessage(String message);
}
