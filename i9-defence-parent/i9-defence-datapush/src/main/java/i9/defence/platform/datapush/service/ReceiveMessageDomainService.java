package i9.defence.platform.datapush.service;

import org.json.JSONObject;

/**
 * 通用消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
public interface ReceiveMessageDomainService {

    /**
     * 处理接收到的消息
     * 
     * @param msg
     */
    void dealWithReceiveMessage(JSONObject msg);

}
