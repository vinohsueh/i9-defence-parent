package i9.defence.platform.socket.netty.handler;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
        if (event instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) event;
            String type = "";
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }
            ctx.close();
            logger.info(ctx.channel().remoteAddress() + "超时类型：" + type);
        } else {
            super.userEventTriggered(ctx, event);
        }
    }
    
    private static final Logger logger = Logger.getLogger(HeartbeatServerHandler.class);
}
