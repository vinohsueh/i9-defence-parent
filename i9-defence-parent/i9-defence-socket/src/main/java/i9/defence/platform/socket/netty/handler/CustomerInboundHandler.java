package i9.defence.platform.socket.netty.handler;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
* 创建时间：2018年5月3日 下午2:48:01
* @author  lby
* @version  
* 
*/
public class CustomerInboundHandler extends ChannelInboundHandlerAdapter {
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    //channel失效处理,客户端下线或者强制退出等任何情况都触发这个方法
	    super.channelInactive(ctx);
	    String channelId = ctx.channel().id().asLongText();
	    logger.info("CustomerInboundHandler 服务器，客户端断开连接 : " + channelId);
    }
    
    private static final Logger logger = Logger.getLogger(ServiceHandler.class);
}
