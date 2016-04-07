/**
 * 
 */
/**
 * @author bwh<bruce.bei@nbmlnx.com>
 * 2016年3月30日
 *
 */
package com.mlnx.chronic.websocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.mlnx.chronic.vo.Data;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {

    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    
    private static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<WebSocketSession>();
    }
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(2);

    private boolean flag = true;
    private int j = 100;
    public SystemWebSocketHandler() {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        users.remove(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        users.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage message)
            throws Exception {
        System.out.println(message.getPayload() + "");
        List<Data> list = new ArrayList<Data>();
        for(double i = 0.5;i<j;i+=0.5){
            Data data = new Data();
            data.setX(i);
            data.setY(20*Math.sin(i/2/Math.PI));
            list.add(data);
        }
        TextMessage tx = new TextMessage(JSON.toJSONString(list));
        System.out.println(sdf.format(new Date())+":"+j);
        j+=100;
        if(j>1000){
            j=100;
        }
        session.sendMessage(tx);
//        sendMessageToUsers(tm);
    }

    @Override
    public void handleTransportError(WebSocketSession session,
            Throwable throwable) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     * 
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.isOpen()) {
                try {
                    user.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送给指定用户
     * 
     * @param username
     * @param message
     */
    public void sendMessageToUser(String username, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("username").equals(username)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}