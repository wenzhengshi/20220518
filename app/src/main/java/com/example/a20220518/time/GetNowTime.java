package com.example.a20220518.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNowTime {
	public static String getNowTime() {
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(d);
		return time;
	}


}
