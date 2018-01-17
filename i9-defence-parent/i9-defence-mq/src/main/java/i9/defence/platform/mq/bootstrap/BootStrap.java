package i9.defence.platform.mq.bootstrap;

import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.mq.service.ConsumerService;
import i9.defence.platform.mq.utils.SpringBeanService;

@SuppressWarnings("serial")
public class BootStrap extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("消息队列服务器已启动");
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                while (true) {
                    try {
                        ConsumerService consumerService = SpringBeanService.getBean(ConsumerService.class);
                        TextMessage textMessage = consumerService.receive();
                        if (textMessage != null) {
                        }
                    }
                    catch (Exception e) {
                    }
                }
            }
        }).start();
    }
}
