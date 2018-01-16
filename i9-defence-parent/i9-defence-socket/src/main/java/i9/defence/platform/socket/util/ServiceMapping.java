package i9.defence.platform.socket.util;

import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.service.impl.HeartbeatService;
import i9.defence.platform.socket.service.impl.LoginService;
import i9.defence.platform.socket.service.impl.PostDataService;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ServiceMapping {

    public HashMap<Byte, Class<?>> dataMap = new HashMap<Byte, Class<?>>();

    @PostConstruct
    public void init() {
        dataMap.put((byte) 0x00, LoginService.class);
        dataMap.put((byte) 0x01, PostDataService.class);
        dataMap.put((byte) 0x03, PostDataService.class);
        dataMap.put((byte) 0xFF, HeartbeatService.class);
    }
    
    public ICoreService getCoreService(byte type) {
        Class<?> clazz = dataMap.get(type);
        ICoreService coreService = (ICoreService) SpringBeanService.getBean(clazz);
        return coreService;
    }
}
