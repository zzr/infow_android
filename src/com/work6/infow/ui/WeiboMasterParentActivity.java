package com.work6.infow.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.work6.infow.R;

public class WeiboMasterParentActivity extends Activity {
	// ======±ÍÃ‚¿∏============
	protected ImageButton custom_left_imagebutton;// ◊Û±ﬂÕº∆¨∞¥≈•
	protected TextView custom_center_text;// ÷–º‰Œƒ◊÷
	protected ImageButton custom_right_imagebutton;// ”“±ﬂÕº∆¨∞¥≈•
	protected String titletext;
	protected int residleft;
	protected int residright;
	// =======================
	// ======Toolbar============
	protected TextView edittv;
	protected boolean isFirst = true;
	protected boolean istopFirst = true;

	// ==========================
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ======±ÍÃ‚¿∏============
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.weibomaster);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.weibomasterparent_customtitle_layout);

		custom_left_imagebutton = (ImageButton) findViewById(R.id.custom_left_imagebutton);
		custom_center_text = (TextView) findViewById(R.id.custom_center_text);
		custom_right_imagebutton = (ImageButton) findViewById(R.id.custom_right_imagebutton);
		
		custom_left_imagebutton.setBackgroundResource(residleft);
//		custom_left_imagebutton.setBackgroundResource(R.drawable.weibomaster_myselectorleft);
		custom_center_text.setText(titletext);
//		custom_center_text.setText("Œ¢≤©");
		custom_right_imagebutton.setBackgroundResource(residright);
//		custom_right_imagebutton.setBackgroundResource(R.drawable.weibomaster_myselectorright);
		// ========================

		// ======Toolbar============
		edittv = (TextView) findViewById(R.id.edit);
		edittv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (isFirst) {
					edittv.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.weibo_item_background));
					isFirst = false;
				} else {
					edittv.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.weibo_touming));
					isFirst = true;
				}
				return false;
			}
		});
		// ==========================
	}
}