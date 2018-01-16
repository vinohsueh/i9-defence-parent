package i9.defence.platform.socket.netty.handler;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.service.impl.LoginService;
import i9.defence.platform.socket.util.ServiceMapping;
import i9.defence.platform.socket.util.SpringBeanService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceHandler extends ChannelInboundHandlerAdapter {
    
    private static final Logger logger = Logger.getLogger(ServiceHandler.class);

    // 客户端每次发送消息接收
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String channelId = ctx.channel().id().asLongText();
        Message message = (Message) msg;
        logger.info("netty 服务器，客户端Id : " + channelId + "发送消息 [type : " + message.getType() + "]");
        MessageDecodeConvert messageDecodeConvert = message.getMessage();
        if (message.getType() == 0x00) {
            ChannelPacker channelPacker = new ChannelPacker(ctx.channel());
            LoginService loginService = SpringBeanService.getBean(LoginService.class);
            loginService.doPost(messageDecodeConvert, channelPacker);
        }
        else {
            ChannelPacker channelPacker = channelPackerServerContext.getChannelPacker(channelId);
            ICoreService coreService = serviceMapping.getCoreService(message.getType());
            coreService.doPost(messageDecodeConvert, channelPacker);
        }
    }
    
    @Autowired
    private ServiceMapping serviceMapping;
    
    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
    // 客户端连接消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String channelId = ctx.channel().id().asLongText();
        logger.info("netty 服务器，客户端连接 : " + channelId);
    }
    
    // 客户端断开连接消息
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String channelId = ctx.channel().id().asLongText();
        logger.info("netty 服务器，客户端断开连接 : " + channelId);
    }
    
    // 异常通知
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("service exception error", cause);
    }
}
