package com.work6.infow.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.work6.infow.R;

public class WeiboMasterParentActivity extends Activity {
	private ImageButton custom_left_imagebutton;
	private TextView custom_center_text;
	private ImageButton custom_right_imagebutton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.weibomasterparent_customtitle_layout);

		custom_left_imagebutton = (ImageButton) findViewById(R.id.custom_left_imagebutton);
		custom_center_text = (TextView) findViewById(R.id.custom_center_text);
		custom_right_imagebutton = (ImageButton) findViewById(R.id.custom_right_imagebutton);

		custom_left_imagebutton
				.setBackgroundResource(R.drawable.weibomaster_myselectorleft);
		custom_left_imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(WeiboMasterParentActivity.this, "left",
						Toast.LENGTH_SHORT).show();
			}
		});

		custom_right_imagebutton
				.setBackgroundResource(R.drawable.weibomaster_myselectorright);
		custom_right_imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(WeiboMasterParentActivity.this, "_right",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}