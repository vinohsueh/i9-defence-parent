package i9.defence.platform.microservice.observer.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessPool {

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public void execute(Runnable runnable) {
        pool.execute(runnable);
    }

    public void startTask() {
        this.execute(consumerRunnable);
    }
    
    @Autowired
    private ConsumerRunnable consumerRunnable;
}
