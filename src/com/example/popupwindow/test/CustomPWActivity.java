package com.example.popupwindow.test;

import com.example.popupwindow.custom.MorePopWindow01;
import com.example.popupwindow.custom.MorePopWindow02;
import com.example.popupwindow.custom.MorePopWindow03;
import com.example.popupwindow.custom.MorePopWindow04;
import com.example.popupwindowtset.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 自定义PopupWindow显示效果
 * @author MrLiu
 * @date 2015/12/08 
 *
 */
public class CustomPWActivity extends Activity implements OnClickListener {

	private Button btn_pw_01;
	private Button btn_pw_02;
	private Button btn_pw_03;
	private Button btn_pw_04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_custom_pw);
		initViews();
		setListeners();
	}

	private void setListeners() {
		btn_pw_01.setOnClickListener(this);
		btn_pw_02.setOnClickListener(this);
		btn_pw_03.setOnClickListener(this);
		btn_pw_04.setOnClickListener(this);
	}

	private void initViews() {
		btn_pw_01 = (Button) findViewById(R.id.btn_pw_01);
		btn_pw_02 = (Button) findViewById(R.id.btn_pw_02);
		btn_pw_03 = (Button) findViewById(R.id.btn_pw_03);
		btn_pw_04 = (Button) findViewById(R.id.btn_pw_04);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_pw_01:  //窗口从父控件左上角出来右上角消失；
			MorePopWindow01 morePopWindow01 = new MorePopWindow01(CustomPWActivity.this);
			morePopWindow01.showPopupWindow(btn_pw_01);
			break;
		case R.id.btn_pw_02:  //窗口从屏幕下方正中间出来，向下渐变消失；
			MorePopWindow02 morePopWindow02 = new MorePopWindow02(CustomPWActivity.this);
			morePopWindow02.showPopupWindow(CustomPWActivity.this.findViewById(R.id.custom_02));
			break;
		case R.id.btn_pw_03:  //弹出的窗口上拉消失动画效果；
			MorePopWindow03 morePopWindow03 = new MorePopWindow03(CustomPWActivity.this);
			morePopWindow03.showPopupWindow(btn_pw_03);
			break;
		case R.id.btn_pw_04:  
			MorePopWindow04 morePopWindow04 = new MorePopWindow04(CustomPWActivity.this);
			morePopWindow04.showPopupWindow(btn_pw_04);
			break;
		}
	}
}
