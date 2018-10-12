package i9.defence.platform.datapush.service;

import org.json.JSONObject;

public interface ReceiveMessagePowerStateService {

    void dealWithUplinkData(JSONObject data);

}
