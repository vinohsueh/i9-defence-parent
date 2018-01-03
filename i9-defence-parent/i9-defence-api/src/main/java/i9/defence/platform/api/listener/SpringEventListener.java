package i9.defence.platform.api.listener;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/** 
 * 创建时间：2017年12月19日 下午3:21:26
 * @author  lby
 * @version  
 * 
 */
@Component
public class SpringEventListener {
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
        AuthorizingRealm realm = (AuthorizingRealm) context.getBean("userRealm");
        manager.setRealm(realm);
    }
}
