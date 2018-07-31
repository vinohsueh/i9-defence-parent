package i9.defence.platform.microservice.sms.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import i9.defence.platform.microservice.sms.listener.ActiveMQSMSConsumerMessageListener;
import i9.defence.platform.mq.libraries.destination.ActiveMQDestination;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

@Configuration
public class ActiveMQBeanConfig {

    @Autowired
    private SingleConnectionFactory connectionFactory;

    @Bean(name = "sms_activemq_destination")
    public ActiveMQQueue getActiveMQDestination() {
        ActiveMQDestination destination = new ActiveMQDestination(ActiveMQQueueEnum.I9_SMS);
        return destination;
    }

    @Autowired
    private ActiveMQSMSConsumerMessageListener activeMQSMSConsumerMessageListener;

    @Bean(name = "sms_activemq_container")
    public DefaultMessageListenerContainer getJmsContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(activeMQSMSConsumerMessageListener);
        container.setDestination(getActiveMQDestination());
        return container;
    }
}
