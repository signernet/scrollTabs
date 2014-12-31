package com.shiji.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 可滚动的栏目条
 * @author Administrator
 *
 */
public class ScrollTabs extends HorizontalScrollView{
	
	private Context mContext;
	private OnScrollListener onScrollListener;
	private LinearLayout ll;
	private LayoutParams llLp;
	private ImageView cursorIv;
	private int mCurPos;
	private TextView currentTabTv;
	private OnItemClickListener mOnItemClickListener;
	private String[] tabsArr;
	
	
	private int mTextColor;
	private int mSelectedTextColor;
	private float mTextSize;
	private float mSelectedTextSize;
	private float mItemWidth;
	
	private float mDTextSize;
	
	int mR = Color.red(mTextColor);
	int mG = Color.green(mTextColor);
	int mB = Color.blue(mTextColor);
	int mSr = Color.red(mSelectedTextColor);
	int mSg = Color.green(mSelectedTextColor);
	int mSb = Color.blue(mSelectedTextColor);
	
	public interface OnItemClickListener{
		void onclick(int pos);
	}
	public ScrollTabs(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		
		TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.ScrollTabs);
		mTextColor = ta.getColor(R.styleable.ScrollTabs_textColor, Color.BLACK);
		mSelectedTextColor = ta.getColor(R.styleable.ScrollTabs_selectedTextColor, Color.BLACK);
		mTextSize = ta.getDimension(R.styleable.ScrollTabs_textSize, 24f);
		mSelectedTextSize = ta.getDimension(R.styleable.ScrollTabs_selectedTextSize, 30f);
		mItemWidth = ta.getDimension(R.styleable.ScrollTabs_tabMinWidth, 50f);
		ta.recycle();
		
		mDTextSize = mSelectedTextSize - mTextSize;
		mR = Color.red(mTextColor);
		mG = Color.green(mTextColor);
		mB = Color.blue(mTextColor);
		mSr = Color.red(mSelectedTextColor);
		mSg = Color.green(mSelectedTextColor);
		mSb = Color.blue(mSelectedTextColor);
		ll = new LinearLayout(context);
		ll.setBackgroundColor(Color.TRANSPARENT);
		llLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		addView(ll,llLp);
		
		setHorizontalScrollBarEnabled(false);
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
	public String[] getTabsArr() {
		return tabsArr;
	}
	public ImageView getCursorIv() {
		return this.cursorIv;
	}
	public LinearLayout getTabsLinearLayout(){
		return this.ll;
	}
	public int getCurrentTab() {
		return mCurPos;
	}
	public TextView getCurrentTabTv() {
		return currentTabTv;
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener!=null){
			onScrollListener.scroll(l, t, oldl, oldt);
		}
	}
	public interface OnScrollListener{
		void scroll(int l, int t, int oldl, int oldt);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}
	
	/**
	 * 选定某一个元素
	 * 更改样式
	 * @param currentTab 当前元素
	 */
	public void setCurrentTab(int currentTab) {
		//首先清理上次的点击的样式
		if(currentTabTv!=null){
			currentTabTv.setTextColor(mTextColor);
			currentTabTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
		}
		
		this.mCurPos = currentTab;
		currentTabTv = (TextView) ll.getChildAt(currentTab);
		currentTabTv.setTextColor(mSelectedTextColor);
		currentTabTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSelectedTextSize);
		System.out.println("这么大"+mSelectedTextSize+"这种色："+mSelectedTextColor);
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(currentTabTv.getWidth(),20); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
//		params.leftMargin = (int) (currentTabTv.getLeft()); //Your X coordinate
//		params.topMargin = 0; //Your Y coordinate
		scrollIfExceed();
		
	}
	
	public void addTab(String name){
		
		TextView tv = new TextView(this.mContext);
		tv.setText(name);
		android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		tv.setPadding(0, 0, 0, 0);
		tv.setMinimumWidth((int)mItemWidth);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(mTextColor);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = ll.indexOfChild(v);
				setCurrentTab(index);
				if(mOnItemClickListener!=null)
					mOnItemClickListener.onclick(index);
			}
		});
		ll.addView(tv,lp);
	}
	

	public TextView getItem(int index){
		return (TextView) ll.getChildAt(index);
	}
	private void scrollIfExceed(){
		if (mCurPos > 0 && mCurPos < ll.getChildCount() - 1) {

			TextView nextTab = (TextView) ll.getChildAt(mCurPos + 1);
			TextView preTab = (TextView) ll.getChildAt(mCurPos - 1);
			int temp = 0;
			if ((temp = currentTabTv.getRight() + nextTab.getWidth() - this.getWidth()
					- this.getScrollX()) > 0
					|| (temp = currentTabTv.getLeft() - preTab.getWidth()
							- this.getScrollX()) < 0) {
				this.smoothScrollBy(temp, 0);
			}
		} else if (mCurPos == 0) {// 如果当前tab是第一个则直接滚动到最前
			this.smoothScrollTo(0, 0);
		} else if (mCurPos == ll.getChildCount() - 1) {// 如果当前tab是最后一个则直接滚动到最后
			this.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
		}
		
	}

	public void onPageScrolled(int position,float offset){
		TextView next = this.getItem(position+1);
		TextView cur = this.getItem(position);
		
		if(cur!=null){
			
			cur.setTextSize(TypedValue.COMPLEX_UNIT_PX,mSelectedTextSize-offset*mDTextSize);
			
			int temp = Color.rgb((int)(mSr-offset*(mSr-mR)), (int)(mSg-offset*(mSg-mG)), (int)(mSb-offset*(mSb-mB)));
			cur.setTextColor(temp);
		}
			
		if(next!=null){
			
			int temp = Color.rgb((int)(mR+offset*(mSr-mR)), (int)(mG+offset*(mSg-mG)), (int)(mB+offset*(mSb-mB)));
			next.setTextColor(temp);
			
			next.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize+offset*mDTextSize);
		}
	}
	

}
