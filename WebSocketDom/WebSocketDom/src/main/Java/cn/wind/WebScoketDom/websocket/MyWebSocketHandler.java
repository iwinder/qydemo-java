package cn.wind.WebScoketDom.websocket;


import cn.wind.WebScoketDom.entity.Message;
import cn.wind.WebScoketDom.utills.StringUtilZ;
import cn.wind.WebScoketDom.utills.UsrMapUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;



import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Created by wind on 2016/11/18.
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private static final Map<String,WebSocketSession> userSocketSessionMap;
    static {
        userSocketSessionMap = new HashMap<String, WebSocketSession>();
    }

    /**
     * 建立连接后的操作
     * @param session
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //spring4.0.2时有个getHandshakeAttributes,没有getAttributes
//        Long uid = (Long)session.getHandshakeAttributes().get("uid");
        //4.0.5时有getAttributes。没有getHandshakeAttribute
        String uid = (String)session.getAttributes().get("uid");
        if(userSocketSessionMap.get(uid)==null){
            userSocketSessionMap.put(uid,session);
        }


    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     * @param session
     * @param message
     * @throws Exception
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message.getPayloadLength()==0)return;
        Message msg = new Gson().fromJson(message.getPayload().toString(),Message.class);
        msg.setDate(StringUtilZ.getTimestampOfNow("yyyy-MM-dd HH:mm:ss"));
        if(!msg.to.equals("all")){
            sendMessageToUser(msg.getFrom(),new TextMessage(new JSONObject(msg).toString()));
        }
        sendMessageToUser(msg.getTo(),new TextMessage(new JSONObject(msg).toString()));
//        sendMessageToUsers();
    }

    /**
     * 消息传输错误处理
     * @param session
     * @param exception
     * @throws Exception
     */
    public void handleTransportError(WebSocketSession session,
             Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        removeSession(session);

    }
    private void removeSession(WebSocketSession session){
        Iterator<Entry<String,WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
        while (it.hasNext()){
            Entry<String,WebSocketSession>entry = it.next();
            if(entry.getValue().getId().equals(session.getId())){
                userSocketSessionMap.remove(entry.getKey());
                if(UsrMapUtil.getUser(entry.getKey())!=null){
                    UsrMapUtil.removeUser(entry.getKey());
                }

                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }
    /**
     * 连接关闭后
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession session,
                  CloseStatus closeStatus) throws Exception {
        System.out.println("Websocket:" + session.getId() + "已经关闭");
        removeSession(session);
    }

    public boolean supportsPartialMessages() {

        return false;
    }

    public void broadcast(final TextMessage message)throws IOException{
        Iterator<Entry<String,WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
        //多线程群发
        while(it.hasNext()){
            final Entry<String,WebSocketSession> entry = it.next();
            if(entry.getValue().isOpen()){
                new Thread(new Runnable() {
                    public void run() {
                        try{
                            if(entry.getValue().isOpen()){
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        }
    }

    private void sendMessageToUser(String uid, TextMessage message) throws IOException{
        if(uid.equals("all")){
            broadcast(message);
        }else{
            WebSocketSession session = userSocketSessionMap.get(uid);
            if(session != null && session.isOpen()){
                session.sendMessage(message);
            }
        }

    }
    private void sendMessageToUsers() {
    }

}
