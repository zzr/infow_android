package com.work6.infow.ui;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.work6.infow.R;

public class NewsAppActivity extends Activity {
	String titletext;
	
	TextView title, tab1, tab2, tab3, tab4, tab5;
	ImageButton leftbutton, rightbutton;
	BroadcastReceiver newsbroadcast;
	AlertDialog dlg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newsmaster);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.newstitlelayout);


		leftbutton = (ImageButton) findViewById(R.id.leftbutton);
		leftbutton.setBackgroundResource(R.drawable.weibomaster_left);
		
		rightbutton = (ImageButton) findViewById(R.id.rightbutton);
		rightbutton.setBackgroundResource(R.drawable.weibomaster_home);
		title = (TextView) findViewById(R.id.title);
		title.setText(titletext);
		
		View dlgview=LayoutInflater.from(NewsAppActivity.this).inflate(R.layout.loadingdialog, null);
		dlg=new AlertDialog.Builder(NewsAppActivity.this).setTitle("情报天下").setView(dlgview).create();
		dlg.show();
		tab1 = (TextView) findViewById(R.id.tab1);
		tab2 = (TextView) findViewById(R.id.tab2);
		tab3 = (TextView) findViewById(R.id.tab3);
		tab4 = (TextView) findViewById(R.id.tab4);
		tab5 = (TextView) findViewById(R.id.tab5);
		tab1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				tab1.setBackgroundResource(R.drawable.tabselected);
				tab2.setBackgroundResource(R.drawable.tabnoselected);
				tab3.setBackgroundResource(R.drawable.tabnoselected);
				tab4.setBackgroundResource(R.drawable.tabnoselected);
				tab5.setBackgroundResource(R.drawable.tabnoselected);

				return false;
			}
		});
		tab2.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				tab1.setBackgroundResource(R.drawable.tabnoselected);
				tab2.setBackgroundResource(R.drawable.tabselected);
				tab3.setBackgroundResource(R.drawable.tabnoselected);
				tab4.setBackgroundResource(R.drawable.tabnoselected);
				tab5.setBackgroundResource(R.drawable.tabnoselected);
				return false;
			}
		});
		tab3.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				tab1.setBackgroundResource(R.drawable.tabnoselected);
				tab2.setBackgroundResource(R.drawable.tabnoselected);
				tab3.setBackgroundResource(R.drawable.tabselected);
				tab4.setBackgroundResource(R.drawable.tabnoselected);
				tab5.setBackgroundResource(R.drawable.tabnoselected);

				return false;
			}
		});
		tab4.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				tab1.setBackgroundResource(R.drawable.tabnoselected);
				tab2.setBackgroundResource(R.drawable.tabnoselected);
				tab3.setBackgroundResource(R.drawable.tabnoselected);
				tab4.setBackgroundResource(R.drawable.tabselected);
				tab5.setBackgroundResource(R.drawable.tabnoselected);

				return false;
			}
		});
		tab5.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				tab1.setBackgroundResource(R.drawable.tabnoselected);
				tab2.setBackgroundResource(R.drawable.tabnoselected);
				tab3.setBackgroundResource(R.drawable.tabnoselected);
				tab4.setBackgroundResource(R.drawable.tabnoselected);
				tab5.setBackgroundResource(R.drawable.tabselected);

				return false;
			}
		});

	}
public ArrayList resolveJSON(JSONObject json){
	return null;
	
}
}
