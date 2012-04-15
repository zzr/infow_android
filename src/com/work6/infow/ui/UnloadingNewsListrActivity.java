package com.work6.infow.ui;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.work6.infow.R;
import com.work6.infow.model.InfowAppUrl;
import com.work6.infow.model.News;
import com.work6.infow.service.NewsAppService;
import com.work6.infow.ui.MyListView.OnRefreshListener;

public class UnloadingNewsListrActivity extends NewsAppActivity {
	Intent i;
MyListView newslist;
final String NEWS_GETNEWSLIST_ACTION="NEWS_GETNEWSLIST_ACTION";
ArrayList<News> newslistdata;
MyAdapter myadapter;
int count=0;
String channelType="",channelId="",page="0",pageSize="10";
//String [] newsdatas={"最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻","最新新闻"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.titletext="新闻列表";
		
		
		super.onCreate(savedInstanceState);
		super.leftbutton.setVisibility(0);
		
		super.rightbutton.setVisibility(4);
		Bundle bd=this.getIntent().getExtras();
		channelType=bd.getString("channelType");
		channelId=bd.getString("channelId");
		newslist=(MyListView) findViewById(R.id.newslist);
		
		newsbroadcast=new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String jsonstring=intent.getExtras().getString("json");
				int count=intent.getExtras().getInt("count");
				JSONObject json = null;
				try {
					json = new JSONObject(jsonstring);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}if(count==0){
				newslistdata= resolveJSON(json);
				myadapter=new MyAdapter(UnloadingNewsListrActivity.this,newslistdata);
				newslist.setAdapter(myadapter);}else{newslistdata= resolveJSON(json);myadapter.notifyDataSetChanged();}
				dlg.dismiss();
			}
		};
		i=new Intent(UnloadingNewsListrActivity.this,NewsAppService.class);
		i.putExtra("url", InfowAppUrl.NEWSLIST+"channelType="+channelType+"&channelId="+channelId+"&page="+page+"&pageSize="+pageSize);
		i.putExtra("action", NEWS_GETNEWSLIST_ACTION);
		i.putExtra("count",count++);
		startService(i);
		newslist.setonRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						startService(i);
						newslist.onRefreshComplete();
					}

				}.execute();
			}
		});
		leftbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(UnloadingNewsListrActivity.this,NewsMasterActivity.class);
				startActivity(i);
			}
		});
		
		newslist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i=new Intent(UnloadingNewsListrActivity.this,NewsDetailActivity.class);
				i.putExtra("news", newslistdata.get(arg2));
				startActivity(i);
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
		ArrayList<News> newslist=new ArrayList<News>();
		// TODO Auto-generated method stub
		try {
			JSONArray jsonarray=json.getJSONArray("newsList");
			for(int i=0;i<jsonarray.length();i++){
				News news=new News();
				JSONObject jsonobject=jsonarray.getJSONObject(i);
				news.setNewsContent(jsonobject.getString("newsContent").toString());
				news.setNewsId(jsonobject.getString("newsId").toString());
				news.setNewsTime(jsonobject.getString("newsTime").toString());
				news.setNewsTitle(jsonobject.getString("newsTitle").toString());
				newslist.add(news);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newslist;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter=new IntentFilter();
		filter.addAction(NEWS_GETNEWSLIST_ACTION);
		registerReceiver(newsbroadcast, filter);
		
	}
	class MyAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
ArrayList<News> newslist1 = null;

	public MyAdapter(Context context,ArrayList<News> newslist1) {
		mInflater = LayoutInflater.from(context);
		this.newslist1 = newslist1;
	}

	@Override
	public int getCount() {
		return newslist1.size();
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
			convertView = mInflater.inflate(R.layout.newslistitem, null);
			holder = new ViewHolder();

			holder.newscount = (TextView) convertView
					.findViewById(R.id.newscount);
			
			holder.newstitle = (TextView) convertView
					.findViewById(R.id.newstitle);
		


			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
	holder.newscount.setText(String.valueOf(position+1)+".");
		holder.newstitle.setText(newslist1.get(position).getNewsTitle());
		
		return convertView;
	}



class ViewHolder {
	TextView newscount,newstitle;
	
}
}

}
