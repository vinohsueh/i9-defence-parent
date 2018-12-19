package i9.defence.platform.aliyun.demo;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;

public class AliyunOutboundDemo {

    public static void main(String[] args) {
        // 阿里云accessKey
        String accessKey = "LTAIHdtHM8kCVlbO";
        // 阿里云accessSecret
        String accessSecret = "gOLAJr55Zet82zVDP22ff46Q6Jc3Iu";
        // regionId
        String regionId = "cn-shanghai";
        // 阿里云uid
        String uid = "1405029890082385";
        // endPoint: https://${uid}.iot-as-http2.${region}.aliyuncs.com
        String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";

        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);

        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);

        // 数据接收
        client.connect(messageToken -> {
            Message m = messageToken.getMessage();
            System.out.println("receive message from " + m);
            return MessageCallback.Action.CommitSuccess;
        });

    }
}
