package i9.defence.platform.socket.netty.handler;

import org.apache.log4j.Logger;

import i9.defence.platform.netty.libraries.ErrorCode;
import i9.defence.platform.netty.libraries.MessageEncodeConvert;
import i9.defence.platform.netty.libraries.resp.CompleteRespMessage;
import i9.defence.platform.netty.libraries.resp.SimpleRespMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.exception.BusinessException;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
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
        ChannelPackerServerContext channelPackerServerContext = SpringBeanService.getBean(ChannelPackerServerContext.class);
        ChannelPacker channelPacker = channelPackerServerContext.getChannelPacker(channelId);
        try {
            ServiceMapping serviceMapping = SpringBeanService.getBean(ServiceMapping.class);
            ICoreService coreService = serviceMapping.getCoreService(message.getType());
            coreService.doPost(message, channelPacker);
            MessageEncodeConvert messageEncodeConvert = message.getMessageEncodeConvert();
            if (messageEncodeConvert == null) {
                CompleteRespMessage completeRespMessage = new CompleteRespMessage(message.getType());
                channelPacker.writeAndFlush(completeRespMessage, message.getIndex());
            }
            else {
                messageEncodeConvert.setType(message.getType());
                channelPacker.writeAndFlush(messageEncodeConvert, message.getIndex());
            }
        }
        catch (BusinessException businessException) {
            int errorCode = businessException.getErrorCode();
            SimpleRespMessage simpleRespMessage = new SimpleRespMessage(message.getType(), errorCode);
            channelPacker.writeAndFlush(simpleRespMessage, message.getIndex());
            logger.info("netty 服务器，客户端 : " + channelId + ", exception : ", businessException);
        }
        catch (Exception exception) {
            int errorCode = ErrorCode.UNKOWN;
            SimpleRespMessage simpleRespMessage = new SimpleRespMessage(message.getType(), errorCode);
            channelPacker.writeAndFlush(simpleRespMessage, message.getIndex());
            logger.info("netty 服务器，客户端 : " + channelId + ", exception : ", exception);
        }
    }
    
//    @Autowired
//    private ServiceMapping serviceMapping;
//    
//    @Autowired
//    private ChannelPackerServerContext channelPackerServerContext;
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
    // 客户端连接消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String channelId = ctx.channel().id().asLongText();
        logger.info("netty 服务器，客户端连接 : " + channelId);
        ChannelPackerServerContext channelPackerServerContext = SpringBeanService.getBean(ChannelPackerServerContext.class);
        ChannelPacker channelPacker = new ChannelPacker(ctx.channel());
        channelPackerServerContext.addChannelPacker(channelPacker);
    }
    
    // 客户端断开连接消息
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String channelId = ctx.channel().id().asLongText();
        ChannelPackerServerContext channelPackerServerContext = SpringBeanService.getBean(ChannelPackerServerContext.class);
        channelPackerServerContext.removeChannelPacker(channelId);
        logger.info("netty 服务器，客户端断开连接 : " + channelId);
    }
    
    // 异常通知
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("service exception error", cause);
    }
}
