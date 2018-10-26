package i9.defence.platform.api.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import i9.defence.platform.cache.ErrorTypeCache;

/**
 * 创建时间：2018年4月14日 上午2:37:34
 * 
 * @author lby
 * @version
 * 
 */
@Component
public class ErrorTypeCacheListener {

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        System.out.println("++++++++++++++++++　　故障类型字典已缓存　　+++++++++++++++++++++");
        ApplicationContext context = event.getApplicationContext();
        ErrorTypeCache errorTypeCache = (ErrorTypeCache) context.getBean("errorTypeCache");
        errorTypeCache.init(); // 调用数据字典的一个方法来缓存
    }

}
