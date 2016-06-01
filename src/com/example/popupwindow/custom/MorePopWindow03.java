package com.example.popupwindow.custom;

import com.example.popupwindowtset.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 点击更多按钮弹出PopupWindow 
 * @style 正左下方弹出，滑动上拉消失或者点击外部消失
 * @author MrLiu
 * @date 2015/12/08
 * 
 */

public class MorePopWindow03 extends PopupWindow {
	
	private View conentView; //存放popupwindow的布局；
	private TextView tv_remind;

	public MorePopWindow03(final Activity context) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.pw_more_03, null);
		
		//设置弹框消失操作的提醒文字的动画；
		Animation ani = new AlphaAnimation(0f, 1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE); //反方向重复模式
		ani.setRepeatCount(Animation.INFINITE); //无限重复
		tv_remind=(TextView) conentView.findViewById(R.id.tv_remind);
		tv_remind.startAnimation(ani);
		
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
		
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, 0, 5); //出现在父控件正左下方，x、y方向有偏移；
		} else {
			this.dismiss();
		}
	}
}
