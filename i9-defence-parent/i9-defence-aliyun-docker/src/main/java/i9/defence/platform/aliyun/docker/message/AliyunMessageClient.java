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
    /**
     * 阿里云accessKey
     */
    @Value("${aliyun.accessKey}")
    private String accessKey;
    /**
     * 阿里云accessSecret
     */
    @Value("${aliyun.accessSecret}")
    private String accessSecret;

    @Value("${aliyun.regionId}")
    private String regionId;
    /**
     * 阿里云uid
     */
    @Value("${aliyun.userId}")
    private String userId;
    
    @Autowired
    private AliyunMessageCallback aliyunMessageCallback;

    @PostConstruct
    public void init() {
        String endPoint = "https://" + this.userId + ".iot-as-http2." + this.regionId + ".aliyuncs.com";
        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);
        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);
        // 数据接收
        client.connect(this.aliyunMessageCallback);
    }
}
