package i9.defence.platform.socket.bootstrap;

import i9.defence.platform.socket.netty.SocketServerInitializer;
import i9.defence.platform.socket.service.ProducerService;
import i9.defence.platform.socket.util.SpringBeanService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class BootStrap extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Override
    public void init() throws ServletException {
        super.init();

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup =  new NioEventLoopGroup();
        
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new SocketServerInitializer());
        
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 9000);
        try {
            bootstrap.bind(address).sync();
            logger.info("game server start, ip address : 0.0.0.0 port : 9000");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                while (true) {
                    try {
                        ProducerService producerService = SpringBeanService.getBean(ProducerService.class);
                        producerService.sendMessage(UUID.randomUUID().toString());
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
