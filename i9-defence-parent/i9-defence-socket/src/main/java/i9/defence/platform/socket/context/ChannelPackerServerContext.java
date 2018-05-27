package i9.defence.platform.socket.context;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ChannelPackerServerContext {

    private ConcurrentHashMap<String, ChannelPacker> context = new ConcurrentHashMap<String, ChannelPacker>();
    
    public void addChannelPacker(ChannelPacker channelPacker) {
        this.context.putIfAbsent(channelPacker.getChannelId(), channelPacker);
    }
    
    public void removeChannelPacker(String channelId) {
        this.context.remove(channelId);
    }
    
    public ChannelPacker getChannelPacker(String channelId) {
        ChannelPacker channelPacker = this.context.get(channelId);
        return channelPacker;
    }
    
//    public void init() {
//    	for (ChannelPacker channelPacker : this.context.values()) {
//    		if (channelPacker.canTimeout()) {
//    			channelPacker.channelClose();
//    		}
//    	}
//    }
}
