package i9.defence.platform.socket.context;

import java.util.concurrent.atomic.AtomicBoolean;

import i9.defence.platform.netty.libraries.MessageEncodeConvert;
import i9.defence.platform.netty.libraries.RespMessageBuilder;
import i9.defence.platform.netty.libraries.req.LoginReqMessage;
import i9.defence.platform.socket.util.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ChannelPacker {
    
    public ChannelPacker() {
    }
    
    public String systemId;// 系统编号(十六进制)
    
    public byte loop;// 回路
    
    public String deviceAddress;// 设备地址

    private Channel channel;
    
    public ChannelPacker(Channel channel) {
        this.channel = channel;
        this.isLogin = new AtomicBoolean(false);
    }
    
    public String getChannelId() {
        if (this.channel == null) {
            return StringUtil.EMPTY;
        }
        return this.channel.id().asLongText();
    }
    
    public AtomicBoolean isLogin;
    
    public boolean checkIsLogin() {
        return this.isLogin.get();
    }
    
    public synchronized void saveLoginInfo(LoginReqMessage loginReqMessage) {
        this.isLogin.set(true);
        this.deviceAddress = loginReqMessage.deviceAddress;
        this.systemId = loginReqMessage.systemId;
        this.loop = loginReqMessage.loop;
    }

    public void writeAndFlush(MessageEncodeConvert messageEncodeConvert, int index) {
        ByteBuf buf = RespMessageBuilder.wrapper(messageEncodeConvert, index);
        this.channel.writeAndFlush(buf);
    }
    
    public void disConnection() {
        this.isLogin.set(false);
    }
}
