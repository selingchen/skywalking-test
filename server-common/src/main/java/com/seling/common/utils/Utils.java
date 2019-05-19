package com.seling.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String getNowTime(){
		Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String time = simpleDateFormat.format(date);
        return time;
	}
	
	public static boolean isNull(String str){
		if(null == str || str.length()<=0){
			return true;
		}
		return false;
	}
	
	public static String decodeUTF8(String string){
		if(string == null || string.length()<=0){
			return " ";
		}else{
			try {
				return URLDecoder.decode(string,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return " ";
		}
	}

}
