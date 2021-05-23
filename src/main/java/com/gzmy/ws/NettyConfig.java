package com.gzmy.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author Dravin
 * @date 2021/5/23 上午6:16
 */
@Configuration
@Component
public class NettyConfig {

    @Autowired
    private NettyAccountConfig nettyAccountConfig;

    @Autowired
    private NettyWebSocketChannelInitializer nettyWebSocketChannelInitializer;

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(nettyAccountConfig.getBossThread());
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(nettyAccountConfig.getWorkerThread());
    }

    @Bean(name = "inetSocketAddress")
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(nettyAccountConfig.getPort());
    }


    @Bean(name = "serverBootstrap")
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(nettyWebSocketChannelInitializer);

        b.option(ChannelOption.SO_BACKLOG, nettyAccountConfig.getBacklog())
                .childOption(ChannelOption.SO_KEEPALIVE, nettyAccountConfig.isKeepalive());

        return b;
    }
}