package com.work6.infow.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.work6.infow.R;
import com.work6.infow.model.InfowAppUrl;
import com.work6.infow.model.News;
import com.work6.infow.service.NewsAppService;

public class NewsDetailActivity extends Activity {
	News news;
	
	AlertDialog dlg;
	final String NEWS_GETNEWSDETAIL_ACTION = "NEWS_GETNEWSDETAIL_ACTION";
	TextView title,detailtitle,detailtime;
	WebView newsdetail;
	ImageButton leftbutton, rightbutton;
	BroadcastReceiver newsbroadcast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newsdetail);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.newstitlelayout);
		detailtitle=(TextView) findViewById(R.id.newsdetailtitle);
		detailtime=(TextView) findViewById(R.id.newsdetailtime);
		newsdetail=(WebView)findViewById(R.id.newsdetail);
		leftbutton = (ImageButton) findViewById(R.id.leftbutton);
		leftbutton.setBackgroundResource(R.drawable.weibomaster_left);
		
		rightbutton = (ImageButton) findViewById(R.id.rightbutton);
		rightbutton.setBackgroundResource(R.drawable.weibomaster_home);
		title = (TextView) findViewById(R.id.title);
		title.setText("新闻内容");
		View dlgview=LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.loadingdialog, null);
		dlg=new AlertDialog.Builder(NewsDetailActivity.this).setTitle("情报天下").setView(dlgview).create();
		Bundle bd=this.getIntent().getExtras();
		
		 News news1=bd.getParcelable("news");
		Intent i=new Intent(NewsDetailActivity.this,NewsAppService.class);
		i.putExtra("url", InfowAppUrl.NEWSDETAIL+"newsId="+news1.getNewsId());
		i.putExtra("action", NEWS_GETNEWSDETAIL_ACTION);
		i.putExtra("count",0);
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
					news= resolveJSON(json);
					detailtitle.setText(news.getNewsTitle());
					detailtime.setText(news.getNewsTime());
					final String mimeType = "text/html";  
					final String encoding = "utf-8";  
					String html=news.getNewsContent();
					newsdetail.loadDataWithBaseURL(null, html,mimeType, encoding, "");  
				dlg.dismiss();
			}
		}
		
	};
	startService(i);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(newsbroadcast);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter=new IntentFilter();
		filter.addAction(NEWS_GETNEWSDETAIL_ACTION);
		registerReceiver(newsbroadcast, filter);
		
	}
	public News resolveJSON(JSONObject json) {
		 News news1 = new News();
		// TODO Auto-generated method stub
		try {
			
			JSONObject jsonobject = json.getJSONObject("news");
			news1.setNewsContent(jsonobject.getString("newsContent"));
			news1.setNewsId(jsonobject.getString("newsId"));
			news1.setNewsTime(jsonobject.getString("newsTime"));
			news1.setNewsTitle(jsonobject.getString("newsTitle"));
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news1;
	}
}
