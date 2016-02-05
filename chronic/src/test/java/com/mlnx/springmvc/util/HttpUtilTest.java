package com.mlnx.springmvc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mlnx.chronic.entity.TProvince;
import com.mlnx.chronic.util.HttpUtil;
import com.mlnx.chronic.vo.UsrInfo;

public class HttpUtilTest {

	@Test
	public void test() throws IOException { // "http://121.40.137.14:8080/pms-sever/rest/bloodPressure/get/82121/1443196800000/1443283200000")
		String sr = HttpUtil
				.sendGet("http://121.40.137.14:8443/pms-sever/rest/bloodPressure/get/82121/1443196800000/1443283200000");
		System.out.println(sr);
	}

	@Test
	public void abc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2015-09-26 00:00:00");
		System.out.println(date.getTime());
		Date date2 = sdf.parse("2015-09-26 15:00:00");
		System.out.println(date2.getTime());
	}

	@Test
	public void getProvince() throws IOException {
		String sr = HttpUtil
				.sendGet("http://121.40.137.14:80/doc/provinces/all.do");
		List<TProvince> provinces = JSON.parseArray(sr, TProvince.class);
		System.out.println(provinces.size());
	}

	@Test
	public void getFriendByPhone() throws IOException {
		PrintWriter out = null;
		String result = "";
		BufferedReader in = null;
		String urlStr = "http://121.40.137.14:80/chronic/user/find/friend/by/phone";
		URL realUrl = new URL(urlStr);
		URLConnection connection = realUrl.openConnection();
		connection.setRequestProperty("Content-type", "application/json");
		connection.setRequestProperty("phone", "15867404045");
		// 发送POST请求必须设置如下两行
		connection.setDoOutput(true);
		connection.setDoInput(true);
		// 获取URLConnection对象对应的输出流
		out = new PrintWriter(new OutputStreamWriter(
				connection.getOutputStream()));
		// 发送请求参数
		out.write("");
		// flush输出流的缓冲
		out.flush();
		in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		System.out.println(result);
		JSONObject vo = JSON.parseObject(result);
		ResponseVo<UsrInfo> usr = new ResponseVo<UsrInfo>();
		System.out.println(vo.get("responseCode"));
		System.out.println(vo.get("msg"));
		System.out.println(vo.get("obj"));
		System.out.println(vo.get("objList"));
		System.out.println(vo.toString());

	}

	@Test
	public void getRealTimeEcg() {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		InputStream is = null;
		HttpURLConnection connection = null;
		try {
			String httpUrl = "http://121.40.137.14:8080/pms-server/rest/rtecg/40024";
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-MLNX-ECG-Lead",
					"I,II,III,aVR,aVL,aVF,V1,V2,V3,V4,V5,V6");
			connection.connect();
			Map map = connection.getHeaderFields();
			for (Object key : map.keySet()) {
				System.out.println(key + " : " + map.get(key));
			}

			System.out.println(connection
					.getHeaderField(HttpConstants.BATTERY_REMAINING_HEADER));

			is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		System.out.println(result);
	}
}
