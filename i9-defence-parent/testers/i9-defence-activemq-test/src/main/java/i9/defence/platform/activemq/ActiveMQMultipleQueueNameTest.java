package i9.defence.platform.microservice.mq;

import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class ActiveMQMultipleQueueNameTest {
    
    @Autowired
    private ActiveMQConsumerService activeMQConsumerService;

    @Test
    public void main() throws Exception {
        String[] path = { "classpath:spring_activemq.xml" };
        ApplicationContext context = new FileSystemXmlApplicationContext(path);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ActiveMQQueue destination = new ActiveMQQueue("i9.test.01");
                    System.out.println("111111111111111111111");
                    activeMQConsumerService.receive(destination);
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ActiveMQQueue destination = new ActiveMQQueue("i9.test.02");
                    System.out.println("111111111111111111111");
                    activeMQConsumerService.receive(destination);
                }
            }
        });
        System.in.read();
    }
}
