package cn.wind.WebServiceE.controller;

import cn.wind.WebServiceE.entity.Content;
import cn.wind.WebServiceE.services.IContentService;
import cn.wind.WebServiceE.utills.StringUtilZ;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import static cn.wind.WebServiceE.controller.MyWebSocket.addOnlineCount;


/**
 * Created by wind on 2016/11/16.
 */

@ServerEndpoint(value = "/websocket",configurator = SpringConfigurator.class)
public class MyWebSocket {
    private static int onlineCount = 0;
    public MyWebSocket(){
    }
    @Autowired
    private IContentService contentService;
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    private Session session;

    /**
     * 链接建立成功调用的方法
     * @param session 可选的参数，session为与某个客户端连接的会话，需要通过它来给客户发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
//        session.getAttributes();
        webSocketSet.add(this);//加入set中
        addOnlineCount();//在线人数加1
        System.out.println("有新链接加入！当前在线人数为："+getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);//从set中删除
        subOnlineCount();
        System.out.println("有一个连接关闭！当前在线人数为："+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息："+message);
        //群发消息
        for(MyWebSocket item:webSocketSet){
            try{
                if(item.session == session){
                    item.sendMessage("<p><span style='color:#ff0000;'>我说："+message+"</span>  <span style='color:#bbbaba;'>当前在线人数："+getOnlineCount()+"</span></p>");
                }else{
                    item.sendMessage("<p>"+message+"  当前在线人数："+getOnlineCount()+"</p>");
                }

            }catch (IOException e){
                e.printStackTrace();
                continue;
            }
        }
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 这个方法与上面几个方法不一样，没有用注解，根据自己需要添加的方法
     * @param message
     */
    private void sendMessage(String message) throws IOException {
        //保存到数据库
        Content content = new Content();
        content.setContent(message);
        content.setCreateDate(StringUtilZ.getTimestampOfNow("yyyy-MM-dd HH:mm:ss"));


        this.session.getBasicRemote().sendText(message);

    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }
    private static synchronized void subOnlineCount(){
        MyWebSocket.onlineCount--;
    }
}
