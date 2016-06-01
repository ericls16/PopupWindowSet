package com.example.popupwindow.test;

import com.example.popupwindowtset.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 应用的入口
 * @author MrLiu
 * @date 2015/12/08
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button btn_showPWLocation;
	private Button btn_custom_pw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setListeners();
	}

	private void initViews() {
		btn_showPWLocation = (Button) findViewById(R.id.btn_showPWLocation);
		btn_custom_pw = (Button) findViewById(R.id.btn_custom_pw);
	}

	private void setListeners() {
		btn_showPWLocation.setOnClickListener(this);
		btn_custom_pw.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_showPWLocation: // 固定显示的位置
			startActivity(new Intent(this, ShowPWLocationActivity.class));
			break;
		case R.id.btn_custom_pw: // 固定显示的位置
			startActivity(new Intent(this, CustomPWActivity.class));
			break;
		}
	}

}
