package com.example.popupwindow.custom;

import com.example.popupwindowtset.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

/**
 * 点击更多按钮弹出PopupWindow 
 * @style 下方弹出
 * @author MrLiu
 * @date 2015/12/08
 * 
 */

public class MorePopWindow02 extends PopupWindow {
	
	private View conentView; //存放popupwindow的布局；

	public MorePopWindow02(final Activity context) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.pw_more_02, null);
		
		//获取context窗口的宽高；
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 2 + 50);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		//当前窗体外围可触摸，加这个貌似没什么效果，这里可以不加；
		this.setOutsideTouchable(true);
		
		// 刷新状态，家或者不加并没有什么影响。
		this.update();
		
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.mypopwindow_anim_style);

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0); //出现在父控件下方正中间，无偏移。
		} else {
			this.dismiss();
		}
	}
}
