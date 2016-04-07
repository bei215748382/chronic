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
import java.nio.ShortBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.mlnx.chronic.ecg.ECGDeviceConstant;
import com.mlnx.chronic.ecg.EcgLead;
import com.mlnx.chronic.entity.Ecg;
import com.mlnx.chronic.repo.EcgRepository;

@Component
public class ECGWebSocketHandler implements WebSocketHandler {

    @Autowired
    private EcgRepository ecgRepository;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    
    private static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<WebSocketSession>();
    }


    public ECGWebSocketHandler() {

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
        System.out.println(message.toString());
        String ecg = JSON.toJSONString(getEcg());
        System.out.println(ecg);
        session.sendMessage(new TextMessage(ecg));
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
    
    private EcgResponse getEcg() throws ParseException{
        int patientId = 2;
        
        Iterator<Ecg> results = ecgRepository.findByPatientIdAndStartDateTime(
                2, sdf.parse("20151008 00:00:00"));
        final int estimatedNumDataPoints = 600;
        List<float[]> dataPoints = new ArrayList<float[]>(estimatedNumDataPoints);
        List<Double> x = new ArrayList<Double>(estimatedNumDataPoints);
        double xValue = 0;
        List<Integer> sampling_rates = new ArrayList<Integer>();
        Date actualStartDateTime = null;
        Date actualEndDateTime = null;
        
        String[] leadNames = new String[]{"I"};
        List<EcgLead> leadList = new ArrayList<EcgLead>(leadNames.length);
        for (String leadName : leadNames) {
            leadName = leadName.trim();
            if (!leadName.isEmpty()) {
                try {
                    EcgLead ecgLead = EcgLead.valueOf(leadName);
                    leadList.add(ecgLead);
                } catch (IllegalArgumentException e) {
                    String errorMessage = String.format("Invalid lead: %s",
                            leadName);
                    System.out.println(errorMessage);
                }
            }
        }
        
        while (results.hasNext()) {
            Ecg result = results.next();
            sampling_rates.add(result.getSamplingRate());
            if (actualStartDateTime == null) {
                actualStartDateTime = result.getDateTime();
            }
            actualEndDateTime = result.getDateTime();

            int numChannels = result.getNumChannels();
            ShortBuffer shortData = result.getData().asShortBuffer();
            int offset = shortData.position();
            while (offset < shortData.limit()) {
                int indexI = EcgLead.I.getChannelIndex();
                Integer valueI = indexI < numChannels ? (int) shortData
                        .get(offset + indexI) : null;
                int indexII = EcgLead.II.getChannelIndex();
                Integer valueII = indexII < numChannels ? (int) shortData
                        .get(offset + indexII) : null;
                float[] values = new float[leadList.size()];
               
                for (int i = 0; i < values.length; i++) {
                    EcgLead lead = leadList.get(i);
                    int value = 0;
                    if (lead.isDerived()) {
                        if (valueI != null && valueII != null) {
                            value = EcgLead.derive(lead, valueI, valueII);
                        }
                    } else if (lead.getChannelIndex() < numChannels) {
                        value = shortData.get(offset + lead.getChannelIndex());
                    }
                    values[i] = ECGDeviceConstant.ECGConvertor(value&0xFFFF);
                }
                dataPoints.add(values);
                x.add(xValue);
                xValue+=100.0/300;
                System.out.println(xValue);
                offset += numChannels;
            }
        }
        EcgResponse response = new EcgResponse();
        response.setPatientId(patientId);
        response.setLeadList(leadList);
        response.setToken(actualEndDateTime.getTime());
        response.setStartDateTime(actualStartDateTime);
        response.setEndDateTime(actualEndDateTime);
        response.setDataPoints(dataPoints);
        response.setSampling_rates(sampling_rates);
        response.setX(x);
        return response;
    }
}