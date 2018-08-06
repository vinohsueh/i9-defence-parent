package i9.defence.platform.activemq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.utils.AliyunSMSEnum;

public class ActiveMQAliyunSMSTest {
    
    /**
     * 测试调用阿里云远程消息队列
     * @param args
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        String[] path = { "classpath:spring_activemq.xml" };
        final ApplicationContext context = new FileSystemXmlApplicationContext(path);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    JSONObject jsonObject = new JSONObject();
                    // 发送短信模版
                    jsonObject.put("templateNum", AliyunSMSEnum.backA.getTemplateNum());
                    jsonObject.put("phones", "[15822613880]");
                    jsonObject.put("clientNames", "");
                    jsonObject.put("signNames", "");
                    ActiveMQProducerService activeMQProducerService = context.getBean(ActiveMQProducerService.class);
                    activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_SMS, jsonObject.toJSONString());
                }
            }
        });
        System.in.read();
    }
}
