package i9.defence.platform.mq.libraries.destination;

import org.apache.activemq.command.ActiveMQQueue;

public class ActiveMQDestination extends ActiveMQQueue {

    public ActiveMQDestination(ActiveMQQueueEnum activeMQQueueEnum) {
        super(activeMQQueueEnum.name());
    }

}
