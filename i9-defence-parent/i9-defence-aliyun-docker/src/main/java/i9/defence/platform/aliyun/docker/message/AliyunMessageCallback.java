package i9.defence.platform.aliyun.docker.message;

import org.springframework.stereotype.Component;

import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

@Component
public class AliyunMessageCallback implements MessageCallback {

    @Override
    public Action consume(MessageToken messageToken) {
        Message m = messageToken.getMessage();
        System.out.println("receive message from " + m);
        return MessageCallback.Action.CommitSuccess;
    }

}
