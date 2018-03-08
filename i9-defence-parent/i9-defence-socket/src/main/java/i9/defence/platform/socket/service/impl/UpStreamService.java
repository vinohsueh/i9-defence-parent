package i9.defence.platform.socket.service.impl;

import i9.defence.platform.mq.libraries.ProducerService;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.message.req.DataMessage;
import i9.defence.platform.socket.message.req.UpStreamReqMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.DataParseUtil;
import i9.defence.platform.socket.util.EncryptUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class UpStreamService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        UpStreamReqMessage upStreamReqMessage = (UpStreamReqMessage) message.getMessageDecodeConvert();
        upStreamReqMessage.showInfo();
        for (DataMessage dataMessage : upStreamReqMessage.dataList) {
            dataMessage.showInfo();
            logger.info("解析数据包体, 数据类型 : " + dataMessage.dataType 
                    + ", hex : " + EncryptUtils.bytesToHexString(dataMessage.data) 
                    + ", 值 : " + DataParseUtil.parseDataValue(dataMessage.dataType, dataMessage.data));
        }
        JSONObject jsonObject = upStreamReqMessage.toJSONObject();
        String jsonStr = jsonObject.toJSONString();
        try {
            producerService.sendMessage(jsonStr);
            logger.info("发送到MQ服务器消息成功, jsonStr : " + jsonStr);
        }
        catch (Exception exception) {
            logger.error("发送到MQ服务器消息失败, jsonStr : " + jsonStr, exception);
        }
    }
    
    private final static Logger logger = LoggerFactory.getLogger(UpStreamService.class);
    
    @Autowired
    private ProducerService producerService;
}
