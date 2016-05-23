package com.example.mobilesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encoder {
	public static String encode(String password){
		try {
			MessageDigest digest=MessageDigest.getInstance("MD5");//获取数字信息摘要器
			
			byte[] result=digest.digest(password.getBytes());//执行加密操作
			StringBuilder sb=new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				int number=result[i]&0xff;
				String str=Integer.toHexString(number);
				if(str.length()==1){
					sb.append(0);
					sb.append(str);
				}else{
					sb.append(str);
				}
				return sb.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return "";
		
	}

}
