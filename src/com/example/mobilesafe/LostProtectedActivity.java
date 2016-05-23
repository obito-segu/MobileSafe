package com.example.mobilesafe;

import com.example.mobilesafe.utils.Md5Encoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LostProtectedActivity extends Activity implements OnClickListener{
	private SharedPreferences sp;//偏好设置存储对象
	
	//首次进入“手机防盗”的控件对象
	private EditText et_first_dialog_pwd;
	private EditText et_first_dialog_pwd_confirm;
	private Button bt_first_dialog_ok;
	private Button bt_first_dialog_cancle;
	//第二次进入“手机防盗”的控件对象
	private EditText et_normal_dialog_pwd;
	private Button bt_normal_dialog_ok;
	private Button bt_normal_dialog_cancle;
	
	private AlertDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		//判断用户是否设置过密码
		if(isSetupPwd()){
			showNormalEntryDialog();
		}else{
			showFirstEntryDialog();
		}
	}
	private void showFirstEntryDialog() {
		AlertDialog.Builder builder=new Builder(this);
		View view=View.inflate(this, R.layout.first_entry_dialog, null);
		et_first_dialog_pwd=(EditText) view.findViewById(R.id.et_first_dialog_pwd);
		et_first_dialog_pwd_confirm=(EditText) view.findViewById(R.id.et_first_dialog_pwd_confirm);
		bt_first_dialog_ok=(Button) view.findViewById(R.id.bt_first_dialog_ok);
		bt_first_dialog_cancle=(Button) view.findViewById(R.id.bt_first_dialog_cancle);
		bt_first_dialog_ok.setOnClickListener(this);
		bt_first_dialog_cancle.setOnClickListener(this);
		
		builder.setView(view);
		dialog=builder.create();
		dialog.show();
	}
	private void showNormalEntryDialog() {
		AlertDialog.Builder builder=new Builder(this);
		builder.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface arg0) {
				finish();
			}
		});
		View view=View.inflate(this, R.layout.normal_entry_dialog, null);
		et_normal_dialog_pwd=(EditText)view.findViewById(R.id.et_normal_dialog_pwd);
		bt_normal_dialog_ok=(Button)view.findViewById(R.id.bt_normal_dialog_ok);
		bt_normal_dialog_cancle=(Button)view.findViewById(R.id.bt_normal_dialog_cancle);
		
		bt_normal_dialog_cancle.setOnClickListener(this);
		bt_normal_dialog_ok.setOnClickListener(this);
		
		builder.setView(view);
		dialog=builder.create();
		dialog.show();

	}
	private boolean isSetupPwd() {
		String savedpwd=sp.getString("password", "");
		if(TextUtils.isEmpty(savedpwd)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.bt_first_dialog_cancle:
			dialog.cancel();
			finish();
			break;
		case R.id.bt_first_dialog_ok:
			String pwd=et_first_dialog_pwd.getText().toString().trim();
			String pwd_confirm=et_first_dialog_pwd_confirm.getText().toString().trim();
			if(TextUtils.isEmpty(pwd_confirm)||TextUtils.isEmpty(pwd)){
				Toast.makeText(this, "密码不能为空", 1);
				return;
			}
			if(pwd.equals(pwd_confirm)){
				Editor editor=sp.edit();
				editor.putString("password", Md5Encoder.encode(pwd));
				editor.commit();
				dialog.dismiss();
				finish();
			}else{
				Toast.makeText(this, "两次密码不同", 1).show();
				return;
			}
			break;
		case R.id.bt_normal_dialog_cancle:
			dialog.cancel();
			finish();
			break;
		case R.id.bt_normal_dialog_ok:
			String userentrypwd=et_first_dialog_pwd.getText().toString().trim();
			if(TextUtils.isEmpty(userentrypwd)){
				Toast.makeText(this, "密码不能为空", 1).show();
				return;
			}
			String savedpwd=sp.getString("password", "");
			if(savedpwd.equals(Md5Encoder.encode(userentrypwd))){
				Toast.makeText(this, "密码正确", 1).show();
				dialog.dismiss();
				return;
			}else{
				Toast.makeText(this, "密码不对", 1).show();
				return;
			}
			
		}
		
	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		menu.add(1,1,1,"更改标题名称");
//		return true;
//	}
//	/**
//	 * 当一个菜单中的Item被选中时，框架回调该方法，并将所单击的Item传入
//		 */
//		@Override
//		public boolean onOptionsItemSelected(MenuItem item) {
//			//上面定义的id为1
//			if (item.getItemId() == 1) {
//				//获取一个窗体构造器
//				AlertDialog.Builder builder = new Builder(this);
//				//创建一个文本输入框
//				final EditText et = new EditText(this);
//				//设置文本输入框中提示的内容，当单击文本输入框时，该内容会自动消失
//				et.setHint("请输入新的标题名称,可以留空");
//				//将文本输入框添加到窗体对话框上
//				builder.setView(et);
//				//为窗体对话框添加一个“确定”按钮
//				builder.setPositiveButton("确定",
//						new DialogInterface.OnClickListener() {
//							//当单击“确定”按钮时要执行的回调方法
//							public void onClick(DialogInterface dialog, int which) {
//								//获取文本输入框中的内容，并将文本前后的空格去除掉
//								String newname = et.getText().toString().trim();
//								//获取sp对应的编辑器
//								Editor editor = sp.edit();
//								//将修改后的名称保存到sp中，此时数据还只在缓存中
//								editor.putString("newname", newname);
//								//数据真正的被保存到sp对应的文件中
//								editor.commit();
//							}
//						});
//				//创建并显示出窗体对话框
//				builder.create().show();
//			}
//			 return super.onOptionsItemSelected(item);
//		}
//	


}
