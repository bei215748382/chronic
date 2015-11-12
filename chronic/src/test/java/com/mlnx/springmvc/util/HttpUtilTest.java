package com.mlnx.springmvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class HttpUtilTest {

	@Test
	public void test() { //"http://121.40.137.14:8080/pms-sever/rest/bloodPressure/get/82121/1443196800000/1443283200000")
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

}
