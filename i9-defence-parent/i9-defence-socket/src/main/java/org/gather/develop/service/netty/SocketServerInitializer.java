package org.gather.develop.service.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.gather.develop.service.netty.codec.CRCDecoder;
import org.gather.develop.service.netty.codec.CRCEncoder;
import org.gather.develop.service.netty.handler.ServiceHandler;

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
