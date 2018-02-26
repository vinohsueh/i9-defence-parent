package i9.defence.platform.socket.util;

import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.service.impl.HeartbeatService;
import i9.defence.platform.socket.service.impl.LoginService;
import i9.defence.platform.socket.service.impl.UpStreamService;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ServiceMapping {

    public HashMap<Byte, Class<?>> dataMap = new HashMap<Byte, Class<?>>();
    
    public static final byte LOGIN_EVENT = 0x00;
    
    public static final byte UPSTREAM_EVENT_0 = 0x01;

    public static final byte UPSTREAM_EVENT_1 = 0x03;

    public static final byte HEARTBEAT_EVENT = (byte) 0xFF;

    @PostConstruct
    public void init() {
        dataMap.put((byte) LOGIN_EVENT, LoginService.class);
        dataMap.put((byte) UPSTREAM_EVENT_0, UpStreamService.class);
        dataMap.put((byte) UPSTREAM_EVENT_1, UpStreamService.class);
        dataMap.put((byte) HEARTBEAT_EVENT, HeartbeatService.class);
    }
    
    public ICoreService getCoreService(byte type) {
        Class<?> clazz = dataMap.get(type);
        ICoreService coreService = (ICoreService) SpringBeanService.getBean(clazz);
        return coreService;
    }
}
