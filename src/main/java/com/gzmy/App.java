package com.gzmy;

import com.gzmy.ws.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Dravin
 * @date 2021/4/19 上午11:21
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        startWS(context);

    }

    private static void startWS(ApplicationContext context) {
        WebSocketServer webSocketServer = context.getBean(WebSocketServer.class);
        try {
            webSocketServer.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("启动 ws 失败");
        }

    }
}
