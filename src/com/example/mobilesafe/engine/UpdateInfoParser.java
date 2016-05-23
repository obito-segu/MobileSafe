package com.example.mobilesafe.engine;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.example.mobilesafe.domain.UpdateInfo;

public class UpdateInfoParser {
	public static UpdateInfo getUpdateInfo(InputStream is) throws XmlPullParserException, IOException{
		//���һ��PULL����ʵ��
		XmlPullParser parser=Xml.newPullParser();
		//���뽫Ҫ�������ļ���
		parser.setInput(is,"UTF-8");
		
		UpdateInfo info=new UpdateInfo();
		//��ȡ��ǰ�������¼�����
		int type=parser.getEventType();
		
		
		while(type!=XmlPullParser.END_DOCUMENT){
			if(type==XmlPullParser.START_TAG){//��ʼԪ��
				if(parser.getName().equals("version")){//>>����Ҳ�൱��һ�����
					info.setVersion(parser.nextText());
				}else if(parser.getName().equals("description")){
					info.setDescription(parser.nextText());
				}else if(parser.getName().equals("apkurl")){
					info.setApkurl(parser.nextText());
				}
			}type=parser.next();//ͨ��next�����¼�
		}
		return info;
	}
}
