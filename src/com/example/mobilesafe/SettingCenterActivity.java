package com.example.mobilesafe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class SettingCenterActivity extends Activity {
	//用于存储自动更新是否开启的boolean值
	private SharedPreferences sp;
	
	private TextView tv_setting_autoupdate_status;
	private CheckBox cb_setting_autoupdate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.setting_center);
		super.onCreate(savedInstanceState);
		
		sp=getSharedPreferences("config", MODE_PRIVATE);
		cb_setting_autoupdate=(CheckBox) findViewById(R.id.cb_setting_autoupdate);
		tv_setting_autoupdate_status=(TextView) findViewById(R.id.tv_setting_autoupdate_status);
		
		boolean autoupdate=sp.getBoolean("autoupdate", true);
		if(autoupdate){
			tv_setting_autoupdate_status.setText("自动更新已开启");
			cb_setting_autoupdate.setChecked(true);
		}else{
			tv_setting_autoupdate_status.setText("自动更新已关闭");
			cb_setting_autoupdate.setChecked(false);
		}
		cb_setting_autoupdate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Editor editor=sp.edit();
				editor.putBoolean("autodate", isChecked);
				editor.commit();
				if(isChecked){
					tv_setting_autoupdate_status.setTextColor(Color.WHITE);
					tv_setting_autoupdate_status.setText("自动更新已开启");
				}else{
					tv_setting_autoupdate_status.setTextColor(Color.RED);
					tv_setting_autoupdate_status.setText("自动更新已关闭");
				}
				
			}
		});
	}

}
