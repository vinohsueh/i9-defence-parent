package i9.defence.platform.socket.netty;

import i9.defence.platform.socket.netty.codec.MessageDecoder;
import i9.defence.platform.socket.netty.codec.MessageEncoder;
import i9.defence.platform.socket.netty.handler.HeartbeatServerHandler;
import i9.defence.platform.socket.netty.handler.ServiceHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(8*60, 8*60, 8*60));
//        ByteBuf delimiter = Unpooled.copiedBuffer("#".getBytes());
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(2048, delimiter));
        // 字符串解码 和 编码
        pipeline.addLast("decoder", new MessageDecoder());
        pipeline.addLast("encoder", new MessageEncoder());
        // 自己的逻辑Handler
        pipeline.addLast("handler", new ServiceHandler());
        pipeline.addLast(new HeartbeatServerHandler());
    }
}
