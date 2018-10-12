package i9.defence.platform.datapush.service.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import i9.defence.platform.datapush.service.ReceiveMessagePowerStateService;

@Service
public class ReceiveMessagePowerStateServiceImpl implements ReceiveMessagePowerStateService {

    @Override
    public void dealWithUplinkData(JSONObject data) {
    }
}
