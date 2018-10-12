package i9.defence.platform.datapush.service;

import org.json.JSONObject;

public interface ReceiveMessageDomainService {

    /**
     * 处理接收到的消息
     * 
     * @param msg
     */
    void dealWithReceiveMessage(JSONObject msg);

}
