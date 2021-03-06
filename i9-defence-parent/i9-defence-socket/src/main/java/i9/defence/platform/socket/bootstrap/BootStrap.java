package i9.defence.platform.socket.bootstrap;

import java.net.InetSocketAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.socket.netty.SocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@SuppressWarnings("serial")
public class BootStrap extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Override
    public void init() throws ServletException {
        super.init();
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true).handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new SocketServerInitializer());

        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 9000);
        try {
            bootstrap.bind(address).sync();
            logger.info("tcpserver started, ip address : 0.0.0.0 port : 9000");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("tcpserver start error", e);
        }
    }
}
