package cn.wind.WebScoketDom.websocket;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * WebSocket 配置处理器
 * Created by wind on 2016/11/19.
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Resource
    private  MyWebSocketHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(handler,"/websocket.do").addInterceptors(new HandShake());
        registry.addHandler(handler,"/websocket/sockjs.do").addInterceptors(new HandShake()).withSockJS();

    }

}
