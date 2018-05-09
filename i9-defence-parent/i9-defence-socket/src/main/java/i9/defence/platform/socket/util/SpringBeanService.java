package i9.defence.platform.socket.util;

import org.springframework.beans.BeansException;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ContextLoader;

public class SpringBeanService {

    /**
     * 取得所有的spring-bean
     * @param requiredType
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return (T) ContextLoader.getCurrentWebApplicationContext().getBean(requiredType);
    }
    
    /**
     * 取得环境变量信息
     * @return
     */
    public static Environment getEnvironment() {
        Environment environment = (Environment) ContextLoader.getCurrentWebApplicationContext().getBean(Environment.class);
        return environment;
    }

    public static Object getBean(String name) {
        return ContextLoader.getCurrentWebApplicationContext().getBean(name);
    }
}
