package com.gzmy.ws;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dravin
 * @date 2021/4/19 下午12:10
 */
@Component
public class NettyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    MyTextWebSocketFrameHandler myTextWebSocketFrameHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("收到新的链接");
        //websock 协议本身是基于http协议的，所以这边也要使用http解码器
        ch.pipeline().addLast(new HttpServerCodec());
        //以块的方式来写的处理器
        ch.pipeline().addLast(new ChunkedWriteHandler());
        /*
        说明
        1. http数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
        2. 这就就是为什么，当浏览器发送大量数据时，就会发出多次http请求
         */
        ch.pipeline().addLast(new HttpObjectAggregator(8192));

        /*
        说明
        1. 对应websocket ，它的数据是以 帧(frame) 形式传递
        2. 可以看到WebSocketFrame 下面有六个子类
        3. 浏览器请求时 ws://localhost:7000/hello 表示请求的uri
        4. WebSocketServerProtocolHandler 核心功能是将 http协议升级为 ws协议 , 保持长连接
        5. 是通过一个 状态码 101
         */
        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", null, true, 65536 * 10));
        //自定义的handler ，处理业务逻辑
        ch.pipeline().addLast(myTextWebSocketFrameHandler);

    }
}
