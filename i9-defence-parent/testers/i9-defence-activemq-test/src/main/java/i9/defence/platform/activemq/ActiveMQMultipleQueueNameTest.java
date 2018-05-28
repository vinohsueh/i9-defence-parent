package i9.defence.platform.activemq;

import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ActiveMQMultipleQueueNameTest {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        String[] path = { "classpath:spring_activemq.xml" };
        final ApplicationContext context = new FileSystemXmlApplicationContext(path);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ActiveMQConsumerService activeMQConsumerService = context.getBean(ActiveMQConsumerService.class);
                    TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_TEST001);
                    try {
                        System.err.println("接收消息 I9_TEST001 : " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ActiveMQConsumerService activeMQConsumerService = context.getBean(ActiveMQConsumerService.class);
                    TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_TEST002);
                    try {
                        System.err.println("接收消息 I9_TEST002 : " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ActiveMQProducerService activeMQProducerService = context.getBean(ActiveMQProducerService.class);
                    activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_TEST001, UUID.randomUUID().toString());
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ActiveMQProducerService activeMQProducerService = context.getBean(ActiveMQProducerService.class);
                    activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_TEST002, UUID.randomUUID().toString());
                }
            }
        });
        System.in.read();
    }
}
