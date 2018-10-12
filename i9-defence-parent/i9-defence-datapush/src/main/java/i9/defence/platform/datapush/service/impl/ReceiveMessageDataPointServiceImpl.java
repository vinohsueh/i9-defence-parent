package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.service.ReceiveMessageDataPointService;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMessageDataPointServiceImpl implements ReceiveMessageDataPointService {

    @Override
    public void dealWithUplinkData(JSONObject data) {
    }
}
