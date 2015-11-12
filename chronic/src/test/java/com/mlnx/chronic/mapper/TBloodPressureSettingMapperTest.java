package com.mlnx.chronic.mapper;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mlnx.chronic.vo.UserSetting;
public class TBloodPressureSettingMapperTest extends TestBase {
	@Autowired
	public TReportMapper t;
	@Autowired
	public TBloodPressureSettingMapper tb;
	@Test
	public void testSelectSettingByPrimaryKey() {
		UserSetting u = tb.selectSettingByPrimaryKey(12);
		System.out.println(u.getTime());
	}
	
	@Test
	public void testSelectAllSetting() {
		List<UserSetting> u = tb.selectAllSetting();
		System.out.println(u.size());
		for (int i = 0; i < u.size(); i++) {
			System.out.println(u.get(i).getPatient_id());
		}
	}
	@Test
	public void test() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse("2015-11-02 15:24:20");
		Date date2 = sdf.parse("2015-11-03 15:24:20");
		long a = date2.getTime() - date1.getTime();
		System.out.println(a);
		System.out.println(1*24*3600*1000);
	}

}
