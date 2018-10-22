package i9.defence.platform.datapush.service;

import org.json.JSONObject;

/**
 * 设备状态消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
public interface ReceiveMessagePowerStateService {

    /**
     * 处理上行数据消息
     * 
     * @param data
     */
    void dealWithUplinkData(JSONObject data);

}
