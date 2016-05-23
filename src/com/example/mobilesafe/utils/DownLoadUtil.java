package com.example.mobilesafe.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

public class DownLoadUtil {
	public static File getFile(String urlpath,String filepath,ProgressDialog pd){
		try {
			URL url=new URL(urlpath);
			File file=new File(filepath);
			FileOutputStream fos=new FileOutputStream(file);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			int max=conn.getContentLength();
			pd.setMax(max);
			
			InputStream is=conn.getInputStream();
			byte[] buffer=new byte[1024];
			int len=0;
			int process=0;
			while((len=is.read(buffer))!=-1){
				fos.write(buffer,0,len);
				//每读取一次输入流，刷新一次下载进度
				process+=len;
				pd.setProgress(process);
				Thread.sleep(30);//设置睡眠时间以便观察下载进度
			}
			fos.flush();//刷新缓存数据到文件中
			fos.close();
			is.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public static String getFilename(String urlpath){
		return urlpath.substring(urlpath.lastIndexOf("/")+1,urlpath.length());
	}
//	protected void installApk(File file){
//		Intent intent=new Intent();
//		intent.setAction("android.intent.action.VIEW");
//		intent.addCategory("android.intent.category.DEFAULT");
//		
////		intent.setType("application/vnd.android.package-archive");
////		intent.setData(Uri.fromFile(file));
//		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//		startActivity(intent);
//	}
}
