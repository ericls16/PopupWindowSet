package com.example.popupwindow.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * 自定义可滑动布局，布局向上方滑出去消失动画效果
 * 
 * @author MrLiu
 * 
 */
public class PullUpLayoutView extends RelativeLayout {

	private Context mContext;

	private Scroller mScroller;

	private int mLastDownY = 0;

	private int mCurryY;

	private int mDelY;

	private boolean mCloseFlag = false;

	private ImageView mImgView;

	public PullUpLayoutView(Context context) {
		super(context);
		mContext = context;
		setupView();
	}

	public PullUpLayoutView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setupView();
	}

	@SuppressLint("NewApi")
	private void setupView() {

		// 这个Interpolator你可以设置别的 我这里选择的是有弹跳效果的Interpolator
		Interpolator polator = new BounceInterpolator();
		mScroller = new Scroller(mContext, polator);

		// 这里你一定要设置成透明背景,不然会影响你看到底层布局
		this.setBackgroundColor(Color.argb(0, 0, 0, 0));

	}

	// 设置本布局的背景-以id来索引图片；
	public void setBgImage(int id) {
		mImgView.setImageResource(id);
	}

	// 设置本布局的背景-以id来索引图片；
	public void setBgImage(Drawable drawable) {
		mImgView.setImageDrawable(drawable);
	}

	// 推动门的动画,（x位置不变，y位置变动）
	public void startBounceAnim(int startY, int dy, int duration) {
		/**
		 * 当startScroll执行过程中即在duration时间内，computeScrollOffset
		 * 方法会一直返回false，但当动画执行完成后会返回返加true.
		 */
		mScroller.startScroll(0, startY, 0, dy, duration);
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastDownY = (int) event.getY();
			return true;
		case MotionEvent.ACTION_MOVE:
			mCurryY = (int) event.getY();
			mDelY = mCurryY - mLastDownY;
			// 只准上滑有效
			if (mDelY < 0) {
				// 滚动到规定的坐标位置。
				scrollTo(0, -mDelY);
			}

			break;
		case MotionEvent.ACTION_UP:
			mCurryY = (int) event.getY();
			mDelY = mCurryY - mLastDownY;
			if (mDelY < 0) {
				if (Math.abs(mDelY) > this.getHeight() / 2) {
					// 向上滑动超过本view布局高的一半时候 开启向上消失动画
					startBounceAnim(this.getScrollY(), this.getHeight(), 450);
					mCloseFlag = true;
				} else {
					// 向上滑动未超过本view布局高的一半的时候 开启向下弹动动画
					startBounceAnim(this.getScrollY(), -this.getScrollY(), 1000);

				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * postInvalidate执行后，会去调computeScroll 方法，而这个方法里再去调postInvalidate
	 */
	@Override
	public void computeScroll() {

		if (mScroller.computeScrollOffset()) {
			// 动画执行结束computeScrollOffset返回true；
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			// 不要忘记更新界面
			postInvalidate();
		} else {
			if (mCloseFlag) {
				this.setVisibility(View.GONE);
			}
		}
	}

}
