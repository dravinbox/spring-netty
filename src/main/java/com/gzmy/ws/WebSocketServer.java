package com.gzmy.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author Dravin
 * @date 2021/4/19 上午11:22
 */
@Data
@Component
public class WebSocketServer {
    @Autowired
    ServerBootstrap serverBootstrap;

    @Autowired
    InetSocketAddress inetSocketAddress;

    private Channel serverChannel;

    public void start() throws Exception {
        ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress).sync();
        serverChannel = channelFuture.channel().closeFuture().sync().channel();
    }
    @PreDestroy
    public void stop() throws Exception {
        serverChannel.close();
        serverChannel.parent().close();
    }
}
