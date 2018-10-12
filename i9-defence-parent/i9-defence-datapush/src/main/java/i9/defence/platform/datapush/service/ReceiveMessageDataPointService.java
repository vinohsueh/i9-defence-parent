package i9.defence.platform.datapush.service;

import org.json.JSONObject;

public interface ReceiveMessageDataPointService {

    void dealWithUplinkData(JSONObject data);

}
