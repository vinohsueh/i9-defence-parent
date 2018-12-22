package i9.defence.platform.aliyun.docker;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

@Ignore
public class DeviceClientTest {

    private MessageClient client;

    String productKey = "a16IzBxrD85";

    String deviceName = "xvJwUD4MAKp7GmiF7OfJ";

    String deviceSecret = "yzCK9qXMdIW8LJnnev6L6vFSN19wJ8zN";

    String subTopic = "/" + productKey + "/" + deviceName + "/get";

    String pubTopic = "/sys/" + productKey + "/" + deviceName + "/thing/event/property/post";

    @Before
    public void init() {
        String region = "cn-shanghai";
        String endPoint = "https://" + productKey + ".iot-as-http2." + region + ".aliyuncs.com";
        // 客户端设备唯一标记
        String clientId = UUID.randomUUID().toString();
        // 连接配置
        Profile profile = Profile.getDeviceProfile(endPoint, productKey, deviceName, deviceSecret, clientId);
        // 如果是true 那么清理所有离线消息，即qos1 或者 2的所有未接收内容
        profile.setCleanSession(false);
        // 构造客户端
        client = MessageClientFactory.messageClient(profile);
        // 数据接收
        client.connect(messageToken -> {
            Message m = messageToken.getMessage();
            System.out.println("receive message from " + m);
            return MessageCallback.Action.CommitSuccess;
        });
    }

    /**
     * topic订阅
     * 
     * @throws Exception
     */
    @Test
    public void testSubscribe() throws Exception {
        CompletableFuture<Boolean> subFuture = client.subscribe(subTopic);
        System.out.println("sub result : " + subFuture.get());
    }

    /**
     * 发布消息
     * 
     * @throws Exception
     */
    @Test
    public void testPublish() throws Exception {
        byte[] payload = randomMessagePayload();
        MessageToken messageToken = client.publish(pubTopic, new Message(payload, 0));
        System.out.println("publish success, messageId: " + messageToken.getPublishFuture().get().getMessageId());
    }

    private byte[] randomMessagePayload() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method", "thing.event.property.post");
        jsonObject.put("id", deviceName);
        jsonObject.put("version", "1.0");

        JSONObject params = new JSONObject();
        params.put("cpu_usage", new Random().nextInt(100));
        jsonObject.put("params", params);

        byte[] payload = jsonObject.toJSONString().getBytes();
        return payload;
    }
}
