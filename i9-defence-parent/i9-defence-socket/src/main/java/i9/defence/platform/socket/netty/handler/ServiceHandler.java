package i9.defence.platform.socket.netty.handler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.exception.BusinessException;
import i9.defence.platform.socket.message.MessageEncodeConvert;
import i9.defence.platform.socket.message.resp.SimpleRespMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.service.impl.LoginService;
import i9.defence.platform.socket.util.ErrorCode;
import i9.defence.platform.socket.util.ServiceMapping;
import i9.defence.platform.socket.util.SpringBeanService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServiceHandler extends ChannelInboundHandlerAdapter {
    
    private static final Logger logger = Logger.getLogger(ServiceHandler.class);

    // 客户端每次发送消息接收
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String channelId = ctx.channel().id().asLongText();
        Message message = (Message) msg;
        logger.info("netty 服务器，客户端Id : " + channelId + "发送消息 [type : " + message.getType() + "]");
        ChannelPacker channelPacker = channelPackerServerContext.getChannelPacker(channelId);
        try {
            if (message.getType() == 0x00) {
                channelPacker = new ChannelPacker(ctx.channel());
                LoginService loginService = SpringBeanService.getBean(LoginService.class);
                loginService.doPost(message, channelPacker);
            }
            else {
                if (channelPacker == null) {
                    throw new BusinessException(ErrorCode.UNAUTHORIZED);
                }
                ICoreService coreService = serviceMapping.getCoreService(message.getType());
                coreService.doPost(message, channelPacker);
            }
            MessageEncodeConvert messageEncodeConvert = message.getMessageEncodeConvert();
            messageEncodeConvert.setType(message.getType());
            channelPacker.writeAndFlush(messageEncodeConvert);
        }
        catch (BusinessException businessException) {
            int errorCode = businessException.getErrorCode();
            SimpleRespMessage simpleRespMessage = new SimpleRespMessage(message.getType(), errorCode);
            ctx.channel().writeAndFlush(simpleRespMessage.encode());
        }
        catch (Exception exception) {
            int errorCode = ErrorCode.UNKOWN;
            SimpleRespMessage simpleRespMessage = new SimpleRespMessage(message.getType(), errorCode);
            ctx.channel().writeAndFlush(simpleRespMessage.encode());
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
        channelPackerServerContext.removeChannelPacker(channelId);
        logger.info("netty 服务器，客户端断开连接 : " + channelId);
    }
    
    // 异常通知
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("service exception error", cause);
    }
}
