package cn.wind.WebScoketDom.websocket;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 这个的主要作用是取得当前请求中的用户名，
 * 并且保存到当前的WebSocketHandler中，以便确定WebSocketHandler所对应的用户，
 * 具体可参考HttpSessionHandshakeInterceptor
 * Created by wind on 2016/11/19.
 */
public class HandShake implements HandshakeInterceptor {
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if(request instanceof ServerHttpRequest){
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if(session!=null){
                //使用uid区分WebSocketHandler，以便定向发送消息
                String uid = (String) session.getAttribute("uid");
                if(uid !=null){
                    map.put("uid",uid);
                }else{
                    return false;
                }
            }

        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
