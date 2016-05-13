package com.mlnx.chronic.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class Main {

	@Test
	public void testE() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		TimeVo tv = new TimeVo();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMothNow = cal.get(Calendar.DAY_OF_MONTH);

		tv.setYear(yearNow);
		tv.setMonth(monthNow);
		tv.setDay(dayOfMothNow);
		System.out.println(tv.toString());

		cal.set(yearNow, monthNow - 1, 1);
		Date startDate = cal.getTime();
		System.out.println(sdf.format(startDate));

		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = cal.getTime();
		System.out.println(sdf.format(endDate));

		cal.setTime(startDate);
		System.out.println(cal.getTime());
		while (cal.before(endDate)) {
			System.out.println(sdf.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		// while(cal.before()){
		//
		// }

	}

	@Test
	public void testTime() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2000-3-20 13:21:12");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date start = cal.getTime();

		cal.add(Calendar.MONTH, 1);
		Date end = cal.getTime();

		
		cal.setTime(start);
		while(cal.getTime().getTime()<end.getTime()){
			System.out.println(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

	}

	class TimeVo {
		int year;
		int month;
		int day;
		int hour;
		int min;
		int second;

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getHour() {
			return hour;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		@Override
		public String toString() {
			return "TimeVo [year=" + year + ", month=" + month + ", day=" + day
					+ ", hour=" + hour + ", min=" + min + ", second=" + second
					+ "]";
		}

	}
}
