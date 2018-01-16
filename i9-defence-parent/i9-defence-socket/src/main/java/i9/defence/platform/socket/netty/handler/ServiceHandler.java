package i9.defence.platform.socket.netty.handler;

import i9.defence.platform.socket.exception.BusinessException;
import i9.defence.platform.socket.util.SpringBeanService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.esotericsoftware.reflectasm.MethodAccess;

public class ServiceHandler extends ChannelInboundHandlerAdapter {
    
    private static final Logger logger = Logger.getLogger(ServiceHandler.class);

    // 客户端每次发送消息接收
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String data = (String) msg;
        logger.info("设备 : " + ctx.channel().id().asLongText() + ", 接收数据 : " + data);
        List<String> values = new ArrayList<String>();
        try {
            String[] args = data.split(",");
            values.add(args[0]);
            Object springBeanObject = SpringBeanService.getBean(args[0]);
            MethodAccess methodAccess = MethodAccess.get(springBeanObject.getClass());
            List<String> params000 = new ArrayList<String>();
            for (int index = 1; index <= args.length - 1; index ++) {
                params000.add(args[index]);
            }
            for (String s : (String[]) methodAccess.invoke(springBeanObject, "execute", params000)) {
                values.add(s);
            }
//            values.add("MSGOK");
        }
        catch (BusinessException exception) {
//            values.clear();
//            values.add("MSGOK");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : values) {
            stringBuffer.append(",").append(string);
        }
        byte[] dst = stringBuffer.substring(1).getBytes();
        ByteBuf buf = Unpooled.buffer(dst.length);
        buf.writeBytes(dst);
        ctx.channel().writeAndFlush(buf);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
    // 客户端连接消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    }
    
    // 客户端断开连接消息
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
    }
    
    // 异常通知
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("service exception error", cause);
    }
}
