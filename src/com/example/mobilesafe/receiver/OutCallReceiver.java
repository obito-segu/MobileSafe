package com.example.mobilesafe.receiver;

import com.example.mobilesafe.LostProtectedActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OutCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String outnumber=getResultData();//获取广播发送来的数据结果
		
		String enterPhoneBakNumber="110";//设定拨号进入手机防盗的号码
		
		if(enterPhoneBakNumber.equals(outnumber)){
			Intent lostIntent=new Intent(context,LostProtectedActivity.class);
			
			//给手机防盗对应的Activity设置一个新的任务栈
			lostIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(lostIntent);
			
			
			//拦截外拨电话号码，在拨号记录不会显示此号码
			setResultData(null);
		}
		
	}

}
