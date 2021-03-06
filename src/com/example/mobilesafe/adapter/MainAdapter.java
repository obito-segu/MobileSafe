package com.example.mobilesafe.adapter;

//import com.example.mobilesafe.R;

import com.example.mobilesafe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

	private LayoutInflater inflater;//布局填充器,可以将一个布局文件转化为View对象
	
	private Context context;//上下文
	
	private static final int[] icons={R.drawable.widget01,R.drawable.widget02,R.drawable.widget03,
		R.drawable.widget04,R.drawable.widget05,R.drawable.widget06,
		R.drawable.widget07,R.drawable.widget08,R.drawable.widget09,
	};
	private static final String[] names={"手机防盗","通信卫士","软件管理",
		"进程管理","流量统计","手机杀毒",
		"系统优化","高级工具","设置中心"
	};
	
	public MainAdapter( Context context) {
		super();
		this.inflater = (LayoutInflater) 
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	/* 为每一个Item返回一个对应的View
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.main_item, null);
		TextView tv_name=(TextView) view.findViewById(R.id.tv_main_item_name);
		ImageView iv_icon=(ImageView) view.findViewById(R.id.iv_main_item_icon);
		tv_name.setText(names[position]);
		iv_icon.setImageResource(icons[position]);
		return view;
	}

}
