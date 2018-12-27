package i9.defence.platform.aliyun.docker.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

import i9.defence.platform.aliyun.docker.service.UpStreamService;

@Component
public class AliyunMessageCallback implements MessageCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunMessageCallback.class);

    @Override
    public Action consume(MessageToken messageToken) {
        Message m = messageToken.getMessage();
        LOGGER.info("接收阿里云数据 : " + m);
        this.upStreamService.saveUpStream(m.getMessageId(), m.getTopic(), new String(m.getPayload()));
        return MessageCallback.Action.CommitSuccess;
    }

    @Autowired
    private UpStreamService upStreamService;
}
