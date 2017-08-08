package net.alily.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class IDate {

	// 获取当前时间
	public static String refFormatNowDate() {

		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);

		return retStrFormatNowDate;
	}

	//获取当前时间戳
	public static long nowTimeStamp() {
		return System.currentTimeMillis();
	}

	//把时间戳格式化为时间
	public static String formatTimeStamp(long timeStamp) {
		Date date = new Date(timeStamp);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String retStrFormatDate = sdFormatter.format(date);

		return retStrFormatDate;
	}


}
