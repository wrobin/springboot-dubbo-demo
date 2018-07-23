package com.wrobin.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat yMFormat = new SimpleDateFormat("yyyy-MM");
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * 目标月最后一天
	 * @param date
	 * @return
	 */
	public static String getMonthLastSUNDAY(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(yMFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return ymdFormat.format(cal.getTime());
	}


	/**
	 * 目标月头一天
	 * @param date
	 * @return
	 */
	public static String getMonthFirstSUNDAY(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(yMFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		return dateFormat.format(cal.getTime());
	}

	/**
	 * 计算时间差多少秒
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Long dateDiff_mis(String startTime, String endTime,
			String format) throws Exception {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		
		// 获得两个时间的毫秒时间差异
		diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		long sec = diff/ ns;// 计算差多少秒//输出结果
		System.out.println("时间相差：" + sec
				+ "秒。");
		
		return sec;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;

		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static Date StrToDate(String str, String format1) {
		SimpleDateFormat format = new SimpleDateFormat(format1);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}


	/**
	 * 分钟加减
	 * @param dt
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date dt, int minute) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MINUTE, minute);
		return rightNow.getTime();
	}

	/**
	 * 小时加减
	 * @param dt
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date dt, int hour) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.HOUR, hour);
		return rightNow.getTime();
	}

	/**
	 * 日期加减
	 * @param dt
	 * @param day
	 * @return
	 */
	public static Date addDay(Date dt, int day) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_YEAR, day);
		return rightNow.getTime();
	}

	/**
	 * 月份加减
	 * @param dt
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date dt, int month) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MONTH, month);
		return rightNow.getTime();
	}

	/**
	 * 年份加减
	 * @param dt
	 * @param year
	 * @return
	 */
	public static Date addYear(Date dt, int year) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, year);
		return rightNow.getTime();
	}

	/**
	 * 
	 * @Title: format
	 * @Description: TODO(日期转 指定格式的 string)
	 * @param @param date
	 * @param @param format
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sft = new SimpleDateFormat(format);
		return sft.format(date);
	}

	/**
	 * （0：日期相等 1：date1大于date2 -1： date2大于date1）
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return 1;
		} else if (date1.getTime() < date2.getTime()) {
			return -1;
		}
		return 0;
	}


	/**
	 * 根据时长获取时间格式
	 * @param time
	 * @return
	 */
	public static String getTimeFormatFromLength(Long time) {
		if(time == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		long h = time / 3600;
		long m = (time - h * 3600) / 60;
		long s = (time - h * 3600) % 60;

		sb.append(h).append(":").append(m).append(":").append(s );
		return sb.toString();
	}
}
