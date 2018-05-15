package i9.defence.platform.microservice.mq.service;

import java.util.TimerTask;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.service.UpStreamDecodeService;

/** 
* 创建时间：2018年5月14日 下午4:29:26
* @author  lby
* @version  
* 
*/
public class ActiveMQConnectionTask extends TimerTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveMQConnectionTask.class);

    private final UpStreamDecodeService upStreamDecodeService;
    
    private final TextMessage textMessage;

    public ActiveMQConnectionTask(UpStreamDecodeService upStreamDecodeService, TextMessage textMessage) {
        this.upStreamDecodeService = upStreamDecodeService;
        this.textMessage = textMessage;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
