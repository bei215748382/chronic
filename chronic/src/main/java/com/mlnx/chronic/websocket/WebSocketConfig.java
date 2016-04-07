package com.mlnx.chronic.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/webSocketServer").addInterceptors(new MyHandshakeInterceptor());
        registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new MyHandshakeInterceptor()).withSockJS();
        registry.addHandler(echoWebSocketHandler(), "/echo").addInterceptors(new EchoHandshakeInterceptor());
        registry.addHandler(echoWebSocketHandler(), "/sockjs/echo").addInterceptors(new EchoHandshakeInterceptor()).withSockJS();
        registry.addHandler(ecgWebSocketHandler(), "/ecg").addInterceptors(new EchoHandshakeInterceptor());
        registry.addHandler(ecgWebSocketHandler(), "/sockjs/eg").addInterceptors(new EchoHandshakeInterceptor()).withSockJS();
    }
    
    @Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler echoWebSocketHandler(){
        return new EchoWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler ecgWebSocketHandler(){
        return new ECGWebSocketHandler();
    }
}
