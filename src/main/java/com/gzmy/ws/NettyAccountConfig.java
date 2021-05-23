package com.gzmy.ws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Dravin
 * @date 2021/5/23 上午6:10
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyAccountConfig {

    private int port;

    private int bossThread;

    private int workerThread;

    private boolean keepalive;

    private int backlog;
}