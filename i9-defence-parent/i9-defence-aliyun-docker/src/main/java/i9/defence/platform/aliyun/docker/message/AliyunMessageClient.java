package i9.defence.platform.aliyun.docker.message;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;

@Component
public class AliyunMessageClient {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunMessageClient.class);

    @Value("${aliyun.appKey}")
    private String appKey;

    @Value("${aliyun.appSecret}")
    private String appSecret;

    @Value("${aliyun.appSecret}")
    private String productKey;

    @Autowired
    private AliyunMessageCallback aliyunMessageCallback;

    @PostConstruct
    public void init() {
        String endPoint = "https://" + productKey + ".iot-as-http2.cn-shanghai.aliyuncs.com";
        LOGGER.info("加载阿里云配置文件, appKey : " + this.appKey);
        LOGGER.info("加载阿里云配置文件, appSecret : " + this.appSecret);
        LOGGER.info("加载阿里云配置文件, productKey : " + this.productKey);
        LOGGER.info("加载阿里云配置文件, endPoint : " + endPoint);
        // 连接配置
        Profile profile = Profile.getAppKeyProfile(endPoint, appKey, appSecret);
        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);
        // 数据接收
        client.connect(this.aliyunMessageCallback);
    }
}
