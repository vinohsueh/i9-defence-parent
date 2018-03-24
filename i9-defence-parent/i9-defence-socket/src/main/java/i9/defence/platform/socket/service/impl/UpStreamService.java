package i9.defence.platform.socket.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.mq.libraries.business.BusinessProducerService;
import i9.defence.platform.netty.libraries.DataParseUtil;
import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.req.DataMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;
import i9.defence.platform.service.UpStreamOriginService;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class UpStreamService implements ICoreService {
	
	@Autowired
	private UpStreamOriginService streamOriginService;
	
    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        UpStreamReqMessage upStreamReqMessage = (UpStreamReqMessage) message.getMessageDecodeConvert();
        upStreamReqMessage.showInfo();
        for (DataMessage dataMessage : upStreamReqMessage.dataList) {
            dataMessage.showInfo();
            logger.info("解析数据包体, 数据类型 : " + dataMessage.type 
                    + ", hex : " + EncryptUtils.bytesToHexString(dataMessage.data) 
                    + ", 值 : " + DataParseUtil.parseDataValue(dataMessage.type, dataMessage.data));
        }
        JSONObject jsonObject = upStreamReqMessage.toJSONObject();
        
        // TODO 保存json数据 service -> dao - db
        // id jsonstr submitDate
        // t_up_stream_origin
        // t_up_stream_decode
        String jsonStr = jsonObject.toJSONString();
        StreamOrigin streamOrigin = new StreamOrigin();
        streamOrigin.setJsonstr(jsonStr);
        streamOrigin.setSubmitDate(new Date());
        streamOriginService.addStreamOrigin(streamOrigin);
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
    private BusinessProducerService producerService;
}
