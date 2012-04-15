package com.work6.infow.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NewsAppService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getJSONFromURL(String url) {
		StringBuffer sb=null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);

		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entityOut = response.getEntity();

			if (entityOut != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						entityOut.getContent(), "utf-8"));
				sb = new StringBuffer();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("ss", sb.toString());
		return sb.toString();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		String url=intent.getExtras().getString("url");
		String action=intent.getExtras().getString("action");
		int count=intent.getExtras().getInt("count");
		String json=getJSONFromURL(url);
		Intent i=new Intent();
		i.putExtra("json", json);
		i.putExtra("count", count);
		i.setAction(action);
		sendBroadcast(i);
	}
}
