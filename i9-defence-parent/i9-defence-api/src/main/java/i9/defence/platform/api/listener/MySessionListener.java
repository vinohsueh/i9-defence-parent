package i9.defence.platform.api.listener;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** 
* 创建时间：2018年4月10日 上午11:31:49
* @author  lby
* @version  
* 
*/
@Component
public class MySessionListener implements SessionListener {
	
	private static final Logger S_LOGGER = LoggerFactory.getLogger(MySessionListener.class); 
	
	private final AtomicInteger sessionCount = new AtomicInteger(0);
	
	public int getSessionCount() {
        return sessionCount.get();
    }
	@Override
	public void onStart(Session session) {
		sessionCount.incrementAndGet();
		S_LOGGER.info("登录+1=="+sessionCount.get());
		
	}

	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
		S_LOGGER.info("登录退出-1=="+sessionCount.get());
		
	}

	@Override
	public void onExpiration(Session session) {
		sessionCount.decrementAndGet();
		S_LOGGER.info("登录过期-1=="+sessionCount.get());
		
	}

}
