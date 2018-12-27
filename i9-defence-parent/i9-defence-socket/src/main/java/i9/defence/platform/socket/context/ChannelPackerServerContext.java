package i9.defence.platform.socket.context;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ChannelPackerServerContext {

    private ConcurrentHashMap<String, ChannelPacker> context = new ConcurrentHashMap<String, ChannelPacker>();

    public void addChannelPacker(ChannelPacker channelPacker) {
        this.context.putIfAbsent(channelPacker.getDeviceAddress(), channelPacker);
    }

    public void removeChannelPacker(String deviceAddress) {
        this.context.remove(deviceAddress);
    }

    public ChannelPacker getChannelPacker(String deviceAddress) {
        ChannelPacker channelPacker = this.context.get(deviceAddress);
        return channelPacker;
    }
}
