package com.mlnx.springmvc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Http {
	
    public static void main(String[] args) {
    	//获取实时心电
        String httpUrl = "http://121.40.137.14:8080/pms-server/rest/ecg/40023";
        String jsonResult = request(httpUrl);
        System.out.println(jsonResult);
        
    }

    /**
     * @param urlAll
     *            :请求接口
     * 
     * @return 返回结果
     */
    public static String request(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-MLNX-ECG-Lead", "I,II,III,aVR,aVL,aVF,V1,V2,V3,V4,V5,V6");
            connection.setRequestProperty("X-MLNX-ECG-Start", "1438672440000");
            connection.setRequestProperty("X-MLNX-ECG-End","1438672500000");
         
            connection.connect();
               Map map = connection.getHeaderFields();
            for (Object key : map.keySet()) {  
                System.out.println( key + " : " + map.get(key));  
            }  

			InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String requestNext(String token){
    	return null;
    }
}