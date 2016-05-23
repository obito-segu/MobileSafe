package com.example.mobilesafe.receiver;

import com.example.mobilesafe.LostProtectedActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OutCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String outnumber=getResultData();//��ȡ�㲥�����������ݽ��
		
		String enterPhoneBakNumber="110";//�趨���Ž����ֻ������ĺ���
		
		if(enterPhoneBakNumber.equals(outnumber)){
			Intent lostIntent=new Intent(context,LostProtectedActivity.class);
			
			//���ֻ�������Ӧ��Activity����һ���µ�����ջ
			lostIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(lostIntent);
			
			
			//�����Ⲧ�绰���룬�ڲ��ż�¼������ʾ�˺���
			setResultData(null);
		}
		
	}

}
