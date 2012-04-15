package com.work6.infow.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.work6.infow.R;

public class WeiboMasterActivity extends WeiboMasterParentActivity {
	ListView weibolistview;
	private String[] mStrings = { "Œ¢≤©1", "Œ¢≤©2", "Œ¢≤©3", "Œ¢≤©4", "Œ¢≤©5", "Œ¢≤©6",
			"Œ¢≤©7", "Œ¢≤©8","Œ¢≤©9" };
	private String[] pics = {
			"http://192.168.1.105:8080/groupon/images/_0118183145.jpg",
			"http://192.168.1.105:8080/groupon/images/_0131114021.jpg",
			"http://192.168.1.105:8080/groupon/images/_0215190738.jpg",
			"http://192.168.1.105:8080/groupon/images/_0315191046.jpg",
			"http://192.168.1.105:8080/groupon/images/_0118183145.jpg",
			"http://192.168.1.105:8080/groupon/images/_0118183145.jpg",
			"http://192.168.1.105:8080/groupon/images/_0118183145.jpg" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.titletext="Œ¢≤©";
		super.residright = R.drawable.weibomaster_myselectorright;
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.weibomaster);
		// super.custom_left_imagebutton
		// .setBackgroundResource(R.drawable.weibomaster_myselectorleft);
		// super.custom_left_imagebutton.setOnClickListener(new
		// OnClickListener() {
		// public void onClick(View v) {
		// Toast.makeText(WeiboMasterActivity.this, "left",
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		//super.custom_left_imagebutton.setVisibility(8);
		super.custom_left_imagebutton.setVisibility(8);
		super.custom_right_imagebutton
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Toast.makeText(WeiboMasterActivity.this, "_right",
								Toast.LENGTH_SHORT).show();
					}
				});

		weibolistview = (ListView) findViewById(R.id.weibolistview);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		 android.R.layout.simple_list_item_1, mStrings);

		weibolistview.setAdapter(adapter);
	}

//	// ◊‘∂®“Â  ≈‰∆˜
//	class MyAdapter extends BaseAdapter {
//		private LayoutInflater mInflater;
//		// ArrayList<Deal> datas = null;
//		String[] datas = null;
//
//		// public MyAdapter(Context context, ArrayList<Deal> datas) {
//		public MyAdapter(Context context) {
//			mInflater = LayoutInflater.from(context);
//			this.datas = mStrings;
//		}
//
//		public int getCount() {
//			// return datas.size();
//			return datas.length;
//		}
//
//		public Object getItem(int position) {
//			return position;
//		}
//
//		public long getItemId(int position) {
//			return position;
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder holder;
//			if (convertView == null) {
//				convertView = mInflater.inflate(R.layout.listviewitem, null);
//				holder = new ViewHolder();
//				holder.text = (TextView) convertView
//						.findViewById(R.id.textView_content);
//				holder.icon = (WebView) convertView.findViewById(R.id.webView1);
//				holder.icon.setVerticalScrollBarEnabled(false);
//				holder.icon.setHorizontalScrollBarEnabled(false);
//
//				WebSettings websettings = holder.icon.getSettings();
//				websettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
//
//				convertView.setTag(holder);
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//			holder.icon.loadUrl(pics[position]);
//			holder.text.setText(datas[position]);
//			return convertView;
//		}
//
//	}
//
//	class ViewHolder {
//		TextView text;
//		WebView icon;
//	}
}