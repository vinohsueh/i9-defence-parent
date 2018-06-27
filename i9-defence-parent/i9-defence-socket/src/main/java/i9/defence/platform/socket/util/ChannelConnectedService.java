package i9.defence.platform.socket.util;

import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.context.DeviceAttribute;
import i9.defence.platform.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class ChannelConnectedService {

    public void connected(ChannelPacker channelPacker) {
        String deviceAddress = channelPacker.getDeviceAddress();
        ChannelPacker channelPacker000 = context.getChannelPacker(deviceAddress);
        LOGGER.info("设备连接, 登录是否有缓存 : {}", channelPacker000 != null);
        // 如果缓存中已存在设备登录信息
        if (channelPacker000 != null) {
            // 如果两条channelPacker channelid 相同，则代表已完成登录操作
            if (channelPacker.getChannelId().equals(channelPacker000.getChannelId())) {
                LOGGER.info("设备连接重复登录, channelId : {}, 设备地址 : {}, 不做处理", channelPacker000.getChannelId(), deviceAddress);
                return;
            } else {
                LOGGER.info("设备连接登录多通道, channelId : {}, 设备地址 : {}, 关闭多余通道", channelPacker000.getChannelId(),
                        deviceAddress);
                channelPacker000.disConnect();
            }
        }
        context.addChannelPacker(channelPacker);
        sendActiveMQMessage(channelPacker);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelConnectedService.class);

    public void sendActiveMQMessage(ChannelPacker channelPacker) {
        DeviceAttribute attribute = channelPacker.getAttribute();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelPacker.getChannelId());
        jsonObject.put("systemId", attribute.getSystemId());
        jsonObject.put("loop", attribute.getLoop());
        jsonObject.put("deviceAddress", attribute.getAddress());
        jsonObject.put("status", 1);
        jsonObject.put("submitDate", DateUtils.DateNowStr());
        jsonObject.put("channelId", channelPacker.getChannelId());
        activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_DEVICE_STATE, jsonObject.toJSONString());
    }

    @Autowired
    private ChannelPackerServerContext context;

    @Autowired
    private ActiveMQProducerService activeMQProducerService;
}
