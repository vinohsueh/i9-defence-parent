package i9.defence.platform.socket.context;

import i9.defence.platform.netty.libraries.MessageEncodeConvert;
import i9.defence.platform.netty.libraries.RespMessageBuilder;
import i9.defence.platform.socket.util.ServerConstant;
import i9.defence.platform.socket.util.SpringBeanService;
import i9.defence.platform.utils.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ChannelPacker {

    public ChannelPacker() {
    }

    private Channel channel;

    public Channel channel() {
        return this.channel;
    }

    public ChannelPacker(Channel channel) {
        this.channel = channel;
    }

    public String getChannelId() {
        if (this.channel == null) {
            return StringUtil.EMPTY;
        }
        return this.channel.id().asLongText();
    }

    public void writeAndFlush(MessageEncodeConvert messageEncodeConvert, int index, byte version) {
        ByteBuf buf = RespMessageBuilder.wrapper(messageEncodeConvert, index, version);
        this.channel.writeAndFlush(buf);
    }

    public boolean checkLoginState() {
        DeviceAttribute attribute = channel.attr(ServerConstant.D_ID_KEY).get();
        return attribute != null;
    }

    public DeviceAttribute getAttribute() {
        DeviceAttribute attribute = this.channel.attr(ServerConstant.D_ID_KEY).get();
        return attribute;
    }

    public DeviceAttribute getAndRemoveAttribute() {
        DeviceAttribute attribute = this.channel.attr(ServerConstant.D_ID_KEY).getAndRemove();
        return attribute;
    }

    public String getDeviceAddress() {
        DeviceAttribute attribute = this.getAttribute();
        return StringUtil.getDeviceId(attribute.getSystemId(), attribute.getLoop(), attribute.getAddress());
    }

    public void putAttribute(DeviceAttribute attribute) {
        this.channel.attr(ServerConstant.D_ID_KEY).set(attribute);
    }

    public void disConnect() {
        try {
            ChannelPackerServerContext context = SpringBeanService.getBean(ChannelPackerServerContext.class);
            context.removeChannelPacker(getDeviceAddress());
            removeAttribute();
            this.channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeAttribute() {
        this.channel.attr(ServerConstant.D_ID_KEY).remove();
    }
}
