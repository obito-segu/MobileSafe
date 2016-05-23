package com.example.mobilesafe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Setup2Activity extends Activity implements OnClickListener{
	private RelativeLayout rl_setup2_bind;
	private ImageView iv_setup2_bind_status;//显示sim卡绑定与否的状态
	private SharedPreferences sp;//保存sim卡是否被绑定的信息
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup2);
		//rl_setup2_bind=findViewById(R.id.rl_)
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
