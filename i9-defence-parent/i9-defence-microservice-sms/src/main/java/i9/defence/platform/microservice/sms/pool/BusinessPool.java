package i9.defence.platform.microservice.sms.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class BusinessPool {

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public void execute(Runnable command) {
        this.executorService.execute(command);
    }
}
