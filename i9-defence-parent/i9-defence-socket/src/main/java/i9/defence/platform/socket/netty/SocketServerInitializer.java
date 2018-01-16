package i9.defence.platform.socket.netty;

import i9.defence.platform.socket.netty.codec.CRCDecoder;
import i9.defence.platform.socket.netty.codec.CRCEncoder;
import i9.defence.platform.socket.netty.handler.ServiceHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("idleStateHandler", new IdleStateHandler(3600, 3600, 3600));
        pipeline.addLast("crcDecoder", new CRCDecoder());
        pipeline.addLast("transferServerHandler", new ServiceHandler());
        pipeline.addLast("crcEncoder", new CRCEncoder());
    }
}
