package com.example.mobilesafe.engine;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.example.mobilesafe.domain.UpdateInfo;

public class UpdateInfoParser {
	public static UpdateInfo getUpdateInfo(InputStream is) throws XmlPullParserException, IOException{
		//获得一个PULL解析实例
		XmlPullParser parser=Xml.newPullParser();
		//传入将要解析的文件流
		parser.setInput(is,"UTF-8");
		
		UpdateInfo info=new UpdateInfo();
		//获取当前触发的事件类型
		int type=parser.getEventType();
		
		
		while(type!=XmlPullParser.END_DOCUMENT){
			if(type==XmlPullParser.START_TAG){//开始元素
				if(parser.getName().equals("version")){//>>内容也相当于一个结点
					info.setVersion(parser.nextText());
				}else if(parser.getName().equals("description")){
					info.setDescription(parser.nextText());
				}else if(parser.getName().equals("apkurl")){
					info.setApkurl(parser.nextText());
				}
			}type=parser.next();//通过next触发事件
		}
		return info;
	}
}
