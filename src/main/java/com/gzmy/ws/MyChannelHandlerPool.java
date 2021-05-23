package com.gzmy.ws;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Dravin
 * @date 2021/4/19 上午11:31
 */
public class MyChannelHandlerPool {

    /**
     * 群聊
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
