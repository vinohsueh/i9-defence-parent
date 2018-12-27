package i9.defence.platform.microservice.mq.config;

import i9.defence.platform.microservice.mq.listener.ActiveMQBusinessConsumerMessageListener;
import i9.defence.platform.microservice.mq.listener.ActiveMQDeviceStateConsumerMessageListener;
import i9.defence.platform.mq.libraries.destination.ActiveMQDestination;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class ActiveMQBeanConfig {

    @Autowired
    private SingleConnectionFactory connectionFactory;

    @Bean(name = "business_activemq_destination")
    public ActiveMQQueue getBusinessActiveMQDestination() {
        ActiveMQDestination destination = new ActiveMQDestination(ActiveMQQueueEnum.I9_BUSINESS);
        return destination;
    }

    @Bean(name = "devicestatus_activemq_destination")
    public ActiveMQQueue getDeviceStatusActiveMQDestination() {
        ActiveMQDestination destination = new ActiveMQDestination(ActiveMQQueueEnum.I9_DEVICE_STATE);
        return destination;
    }

    @Autowired
    private ActiveMQBusinessConsumerMessageListener activeMQBusinessConsumerMessageListener;

    @Bean(name = "business_activemq_container")
    public DefaultMessageListenerContainer getBusinessJmsContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(activeMQBusinessConsumerMessageListener);
        container.setDestination(getBusinessActiveMQDestination());
        return container;
    }

    @Autowired
    private ActiveMQDeviceStateConsumerMessageListener activeMQDeviceStateConsumerMessageListener;

    @Bean(name = "devicestatus_activemq_container")
    public DefaultMessageListenerContainer getDeviceStatusJmsContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(activeMQDeviceStateConsumerMessageListener);
        container.setDestination(getDeviceStatusActiveMQDestination());
        return container;
    }
}
