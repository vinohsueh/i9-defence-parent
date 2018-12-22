package i9.defence.platform.aliyun.docker.message;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;

@Component
public class AliyunMessageClient {

    @Value("${aliyun.appKey}")
    private String appKey;

    @Value("${aliyun.appSecret}")
    private String appSecret;

    @Value("${aliyun.productKey}")
    private String productKey;

    @Autowired
    private AliyunMessageCallback aliyunMessageCallback;

    @PostConstruct
    public void init() {
        String endPoint = "https://" + productKey + ".iot-as-http2.cn-shanghai.aliyuncs.com";
        // 连接配置
        Profile profile = Profile.getAppKeyProfile(endPoint, appKey, appSecret);
        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);
        // 数据接收
        client.connect(this.aliyunMessageCallback);
    }
}
