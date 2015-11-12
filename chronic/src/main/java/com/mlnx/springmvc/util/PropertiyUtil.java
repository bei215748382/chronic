package com.mlnx.springmvc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;

public class PropertiyUtil {
	private static final Logger log = LoggerFactory
			.getLogger(PropertiyUtil.class);
	
	private static String defaultTBloodPressureSettingProperties = "properties/defaultBloodPressureSetting.properties";
	
	private static String defaultTBloodSugarSettingProperties = "properties/defaultBloodSugarSetting.properties";
	
	public static String serviceProperties = "properties/service.properties";

	public static TBloodPressureSetting getDefaultTBloodPressureSetting() {
		TBloodPressureSetting t = new TBloodPressureSetting();
		Properties p = loadProperty(defaultTBloodPressureSettingProperties);
		t.setHigh(Integer.parseInt(p.getProperty("defaultBloodPressureSetting.high")));
		t.setLow(Integer.parseInt(p.getProperty("defaultBloodPressureSetting.low")));
		t.setPeriodic(Integer.parseInt(p.getProperty("defaultBloodPressureSetting.periodic")));
		System.out.println(t.toString());
		return t;
	}
	
	public static TBloodSugarSetting getDefaultTBloodSugarSetting() {
		TBloodSugarSetting t = new TBloodSugarSetting();
		Properties p = loadProperty(defaultTBloodSugarSettingProperties);
		t.setHigh(Integer.parseInt(p.getProperty("defaultBloodSugarSetting.high")));
		t.setLow(Integer.parseInt(p.getProperty("defaultBloodSugarSetting.low")));
		t.setPeriodic(Integer.parseInt(p.getProperty("defaultBloodSugarSetting.periodic")));
		System.out.println(t.toString());
		return t;
	}
	
	public static Properties loadProperty(String fileName){
		Properties p = new Properties();
		try (InputStream configIn = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(fileName)) {
			p.load(configIn);
		} catch (IOException e) {
			log.info(String.format("load properties %s error:%s",
					fileName, e.getMessage()));
		}
		return p;
	}
	
	public static void main(String[] args) {
		getDefaultTBloodPressureSetting();
	}
}
