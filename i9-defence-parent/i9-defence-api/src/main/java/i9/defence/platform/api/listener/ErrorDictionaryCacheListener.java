package i9.defence.platform.api.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import i9.defence.platform.api.cache.EquipErrorCache;

/** 
 * 故障字典缓存
* 创建时间：2018年3月29日 上午10:18:14
* @author  lby
* @version  
* 
*/
@Component
public class ErrorDictionaryCacheListener {
	
	@EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        EquipErrorCache realm = (EquipErrorCache) context.getBean("equipErrorCache");
    }
}
