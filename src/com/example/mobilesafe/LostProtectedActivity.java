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
	private SharedPreferences sp;//ƫ�����ô洢����
	
	//�״ν��롰�ֻ��������Ŀؼ�����
	private EditText et_first_dialog_pwd;
	private EditText et_first_dialog_pwd_confirm;
	private Button bt_first_dialog_ok;
	private Button bt_first_dialog_cancle;
	//�ڶ��ν��롰�ֻ��������Ŀؼ�����
	private EditText et_normal_dialog_pwd;
	private Button bt_normal_dialog_ok;
	private Button bt_normal_dialog_cancle;
	
	private AlertDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		//�ж��û��Ƿ����ù�����
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
				Toast.makeText(this, "���벻��Ϊ��", 1);
				return;
			}
			if(pwd.equals(pwd_confirm)){
				Editor editor=sp.edit();
				editor.putString("password", Md5Encoder.encode(pwd));
				editor.commit();
				dialog.dismiss();
				finish();
			}else{
				Toast.makeText(this, "�������벻ͬ", 1).show();
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
				Toast.makeText(this, "���벻��Ϊ��", 1).show();
				return;
			}
			String savedpwd=sp.getString("password", "");
			if(savedpwd.equals(Md5Encoder.encode(userentrypwd))){
				Toast.makeText(this, "������ȷ", 1).show();
				dialog.dismiss();
				return;
			}else{
				Toast.makeText(this, "���벻��", 1).show();
				return;
			}
			
		}
		
	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		menu.add(1,1,1,"���ı�������");
//		return true;
//	}
//	/**
//	 * ��һ���˵��е�Item��ѡ��ʱ����ܻص��÷�����������������Item����
//		 */
//		@Override
//		public boolean onOptionsItemSelected(MenuItem item) {
//			//���涨���idΪ1
//			if (item.getItemId() == 1) {
//				//��ȡһ�����幹����
//				AlertDialog.Builder builder = new Builder(this);
//				//����һ���ı������
//				final EditText et = new EditText(this);
//				//�����ı����������ʾ�����ݣ��������ı������ʱ�������ݻ��Զ���ʧ
//				et.setHint("�������µı�������,��������");
//				//���ı��������ӵ�����Ի�����
//				builder.setView(et);
//				//Ϊ����Ի������һ����ȷ������ť
//				builder.setPositiveButton("ȷ��",
//						new DialogInterface.OnClickListener() {
//							//��������ȷ������ťʱҪִ�еĻص�����
//							public void onClick(DialogInterface dialog, int which) {
//								//��ȡ�ı�������е����ݣ������ı�ǰ��Ŀո�ȥ����
//								String newname = et.getText().toString().trim();
//								//��ȡsp��Ӧ�ı༭��
//								Editor editor = sp.edit();
//								//���޸ĺ�����Ʊ��浽sp�У���ʱ���ݻ�ֻ�ڻ�����
//								editor.putString("newname", newname);
//								//���������ı����浽sp��Ӧ���ļ���
//								editor.commit();
//							}
//						});
//				//��������ʾ������Ի���
//				builder.create().show();
//			}
//			 return super.onOptionsItemSelected(item);
//		}
//	


}
