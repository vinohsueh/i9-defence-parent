package i9.defence.platform.microservice.mq.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class ActiveMQBusinessPool {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    public void execute(Runnable command) {
        this.executorService.execute(command);
    }
}
