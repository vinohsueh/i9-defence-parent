package i9.defence.platform.mq.libraries.destination;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ActiveMQDestinationHolder {

    @PostConstruct
    public void init() {
        HashMap<ActiveMQQueueEnum, ActiveMQDestination> dataMap = new HashMap<ActiveMQQueueEnum, ActiveMQDestination>();
        for (ActiveMQQueueEnum activeMQQueueEnum : ActiveMQQueueEnum.values()) {
            ActiveMQDestination activeMQDestination = new ActiveMQDestination(activeMQQueueEnum);
            dataMap.put(activeMQQueueEnum, activeMQDestination);
        }
        this.dataMap = dataMap;
    }
    
    private HashMap<ActiveMQQueueEnum, ActiveMQDestination> dataMap = new HashMap<ActiveMQQueueEnum, ActiveMQDestination>();
    
    public ActiveMQDestination getDestination(ActiveMQQueueEnum activeMQQueueEnum) {
        return this.dataMap.get(activeMQQueueEnum);
    }
}
