package com.example.popupwindow.test;

import com.example.popupwindowtset.R;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 固定PopupWindow显示的位置
 * @author MrLiu
 * @date 2015/12/08 
 *
 */
public class ShowPWLocationActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

	private Button mbutton01;  
    private Button mbutton02;  
    private Button mbutton03;  
    private Button mbutton04;  
    private PopupWindow mPopupWindow;  
    
    // 屏幕的width  
    private int mScreenWidth;  
    // 屏幕的height  
    private int mScreenHeight;  
    // PopupWindow的width  
    private int mPopupWindowWidth;  
    // PopupWindow的height  
    private int mPopupWindowHeight;  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.layout_show_pw_location);  
  
        mbutton01 = (Button) findViewById(R.id.button01);  
        mbutton02 = (Button) findViewById(R.id.button02);  
        mbutton03 = (Button) findViewById(R.id.button03);  
        mbutton04 = (Button) findViewById(R.id.button04);  
  
        mbutton01.setOnClickListener(this);  
        mbutton02.setOnClickListener(this);  
        mbutton03.setOnClickListener(this);  
        mbutton04.setOnClickListener(this);  
    }  
  
    @Override  
    public void onClick(View v) {  
        switch (v.getId()) {  
        // 相对某个控件的位置（正左下方），无偏移  
        case R.id.button01:  
            getPopupWindowInstance();  
            mPopupWindow.showAsDropDown(v);  
            break;  
  
        // 相对某个控件的位置（正左下方），有偏移  
        case R.id.button02:  
            getPopupWindowInstance();  
            mPopupWindow.showAsDropDown(v, 50, 50);// X、Y方向各偏移50  
            break;  
  
        // 相对于父控件的位置，无偏移  
        case R.id.button03:  
            getPopupWindowInstance();  
            mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);  
            break;  
  
        // 相对于父控件的位置，有偏移  
        case R.id.button04:  
            getPopupWindowInstance();  
            mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);  
            break;  
  
        default:  
            break;  
        }  
    }  
  
    @Override  
    public void onCheckedChanged(RadioGroup group, int checkedId) {  
    	
    }  
  
    /* 
     * 获取PopupWindow实例 
     */  
    private void getPopupWindowInstance() {  
        if (null != mPopupWindow) {  
            mPopupWindow.dismiss();  
            return;  
        } else {  
            initPopuptWindow();  
        }  
    }  
  
    /* 
     * 创建PopupWindow 
     */  
    private void initPopuptWindow() {  
    	
        LayoutInflater layoutInflater = LayoutInflater.from(this);  
        View popupWindow = layoutInflater.inflate(R.layout.pw_show_location_style, null);  
        
        RadioGroup radioGroup = (RadioGroup) popupWindow.findViewById(R.id.radioGroup);  
        radioGroup.setOnCheckedChangeListener(this);  
  
        // 创建一个PopupWindow  
        mPopupWindow = new PopupWindow(popupWindow, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);  
  
        // 设置SelectPicPopupWindow弹出窗体可点击
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        
        // 实例化一个ColorDrawable颜色为半透明
     	ColorDrawable dw = new ColorDrawable(0000000000);
     	// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
     	mPopupWindow.setBackgroundDrawable(dw);
     	
        // 获取屏幕和PopupWindow的width和height； 
        mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();  
        mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();  
        mPopupWindowWidth = mPopupWindow.getWidth();  
        mPopupWindowHeight = mPopupWindow.getHeight();  
        
        //打印屏幕和popupwindow的宽高；
        Log.i("Tag", "ScreenWidth : " + mScreenWidth + "\n"
        			+ "ScreenHeight : " + mScreenHeight + "\n"
        			+ "PopupWindowWidth : " + mPopupWindowWidth + "\n"
        			+ "PopupWindowHeight : " + mPopupWindowHeight);
    } 
}
