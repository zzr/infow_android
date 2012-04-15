package com.work6.infow.ui;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.work6.infow.R;
import com.work6.infow.model.Channel;
import com.work6.infow.model.InfowAppUrl;
import com.work6.infow.service.NewsAppService;

public class NewsMasterActivity extends NewsAppActivity {
MyListView newslist;
final String NEWS_GETCHANNELLIST_ACTION="NEWS_GETCHANNELLIST_ACTION";
ArrayList<Channel> channellist;
String userID="";
int count=0;
//String [] newsdatas={"最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.titletext="频道列表";
		
		super.onCreate(savedInstanceState);
		
		super.leftbutton.setVisibility(4);

		
		
		super.rightbutton.setVisibility(4);
		newslist=(MyListView) findViewById(R.id.newslist);
		Intent i=new Intent(NewsMasterActivity.this,NewsAppService.class);
		i.putExtra("url", InfowAppUrl.CHANNALLIST+userID);
		i.putExtra("action", NEWS_GETCHANNELLIST_ACTION);
		i.putExtra("count",count++);
		newsbroadcast=new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				String jsonstring=intent.getExtras().getString("json");
				JSONObject json = null;
				try {
					json = new JSONObject(jsonstring);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				channellist= resolveJSON(json);
				
				newslist.setAdapter(new MyAdapter(NewsMasterActivity.this,channellist));
				dlg.dismiss();
			}
		};
	
		
		startService(i);
		newslist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent ii=new Intent(NewsMasterActivity.this,UnloadingNewsListrActivity.class);
				ii.putExtra("channelType", channellist.get(arg2).getChannelType());
				ii.putExtra("channelId", channellist.get(arg2).getChannelId());
				startActivity(ii);
			}
		});
		tab1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				tab1.setBackgroundResource(R.drawable.tabselected);
				tab2.setBackgroundResource(R.drawable.tabnoselected);
				tab3.setBackgroundResource(R.drawable.tabnoselected);
				tab4.setBackgroundResource(R.drawable.tabnoselected);
				tab5.setBackgroundResource(R.drawable.tabnoselected);
				Intent ii=new Intent(NewsMasterActivity.this,UnloadingNewsListrActivity.class);
				ii.putExtra("channelType", channellist.get(0).getChannelType());
				ii.putExtra("channelId", channellist.get(0).getChannelId());
				startActivity(ii);
				return false;
			}
		});
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(newsbroadcast);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList resolveJSON(JSONObject json) {
		ArrayList<Channel> channellist=new ArrayList<Channel>();
		// TODO Auto-generated method stub
		try {
			JSONArray jsonarray=json.getJSONArray("channelList");
			for(int i=0;i<jsonarray.length();i++){
				Channel channel=new Channel();
				JSONObject jsonobject=jsonarray.getJSONObject(i);
				channel.setChannelId(jsonobject.getString("channelId").toString());
				channel.setChannelName(jsonobject.getString("channelName").toString());
				channel.setChannelSearchStr(jsonobject.getString("channelSearchStr").toString());
				channel.setChannelType(jsonobject.getString("channelType").toString());
				channellist.add(channel);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channellist;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter=new IntentFilter();
		filter.addAction(NEWS_GETCHANNELLIST_ACTION);
		registerReceiver(newsbroadcast, filter);
		
	}
	class MyAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
ArrayList<Channel> channellist1 = null;

	public MyAdapter(Context context,ArrayList<Channel> channellist) {
		mInflater = LayoutInflater.from(context);
		this.channellist1 = channellist;
	}

	@Override
	public int getCount() {
		return channellist1.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		// 当convertview为空时new一个ViewHolder对象，不为空时就使用前一个对象那个，这样可以减少对象，从而提高速度
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.channellistitem, null);
			holder = new ViewHolder();

			holder.channelcount = (TextView) convertView
					.findViewById(R.id.channelcount);
			
			holder.channeltitle = (TextView) convertView
					.findViewById(R.id.channeltitle);


			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
	holder.channelcount.setText(String.valueOf(position+1)+".");
		holder.channeltitle.setText(channellist1.get(position).getChannelName());
		return convertView;
	}



class ViewHolder {
	TextView channelcount,channeltitle;
	
}
}
}
