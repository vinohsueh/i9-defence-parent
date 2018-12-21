package i9.defence.platform.aliyun.demo;

import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iot.as.bridge.core.BridgeBootstrap;
import com.aliyun.iot.as.bridge.core.config.ConfigFactory;
import com.aliyun.iot.as.bridge.core.handler.UplinkChannelHandler;
import com.aliyun.iot.as.bridge.core.model.Session;
import com.aliyun.iot.as.bridge.server.config.impl.BridgeConfigManagerImpl;

import i9.defence.platform.aliyun.demo.utils.SimeleDeviceConfigManager;

public class Application {

    public static void main(String[] args) {
        BridgeBootstrap bootstrap = new BridgeBootstrap();
        ConfigFactory.init(new BridgeConfigManagerImpl(), new SimeleDeviceConfigManager());
        // 不实现下行通讯
        bootstrap.bootstrap();

        String DeviceName = "Iyub03hvXHIaBqDSaJz4";
        UplinkChannelHandler uplinkHandler = new UplinkChannelHandler();
        Session session = Session.newInstance(DeviceName, new Object());
        boolean success = uplinkHandler.doOnline(session, DeviceName);
        if (success) {
            System.out.println("上线成功，接受后续通信请求");
        } else {
            System.out.println("上线失败，拒绝后续通信请求，断开连接");
        }

//        DeviceIdentity identity = ConfigFactory.getDeviceConfigManager().getDeviviceIdentity(device); 
//        
//        Session session = SessionManagerFactory.getInstance().getSession(device); 

//        DeviceIdentity identity = ConfigFactory.getDeviceConfigManager().getDeviviceIdentity(device); 
//        if (identity == null) { 
//            // 设备未映射到阿里云物联网平台上的设备，丢弃消息 
//            return; 
//        } 
//        Session session = SessionManagerFactory.getInstance().getSession(device); 
//        if (session == null) { 
//            // 设备尚未上线，上报数据到阿里云物联网平台前请务必确保设备已上线，请上线设备或者丢弃消息 
//        } 
        String topic = "/sys/a16IzBxrD85/Iyub03hvXHIaBqDSaJz4/thing/event/property/post";
        
        
//        
//        {
//            "method": "thing.service.property.set", 
//            "id": "12345", 
//            "version": "1.0", 
//            "params": {
//                "prop_float": 123.452, 
//                "prop_int16": 333, 
//                "prop_bool": 1
//            }
//        }
        
        while (true) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "thing.event.property.post");
            jsonObject.put("id", DeviceName);
            jsonObject.put("version", "1.0");
            
            JSONObject params = new JSONObject();
            params.put("cpu_usage", new Random().nextInt(100));
            jsonObject.put("params", params);

            byte[] payload = jsonObject.toJSONString().getBytes();
            
            success = uplinkHandler.doPublish(DeviceName, topic, payload, 0, payload.length);
            if (success) {
                // 上报数据到阿里云物联网平台成功
                System.err.println("上报数据到阿里云物联网平台成功" + jsonObject.toJSONString());
            } else {
                // 上报数据到阿里云物联网平台失败
            }
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
