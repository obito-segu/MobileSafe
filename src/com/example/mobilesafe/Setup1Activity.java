package com.example.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class Setup1Activity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup1);
	}
	public void next(View view){
		AlphaAnimation inAnimation=new AlphaAnimation(0.0f, 1.0f);
		AlphaAnimation outAnimation=new AlphaAnimation(1.0f, 0.0f);
		inAnimation.setDuration(300);
		outAnimation.setDuration(300);
		startActivity(new Intent(this,Setup2Activity.class));
		finish();
		//overridePendingTransition(inAnimation, outAnimation);
	}
}
