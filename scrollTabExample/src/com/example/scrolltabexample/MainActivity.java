package com.example.scrolltabexample;

import android.app.Activity;
import android.os.Bundle;

import com.shiji.view.ScrollTabs;

public class MainActivity extends Activity {
	
	private ScrollTabs mScrollTabs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mScrollTabs = (ScrollTabs) findViewById(R.id.main_scrollTabs);
		mScrollTabs.addTab("Headline");
		mScrollTabs.addTab("Nation");
		mScrollTabs.addTab("Focus");
		mScrollTabs.addTab("Education");
		mScrollTabs.addTab("Comment");
		mScrollTabs.addTab("Recruitment");
		mScrollTabs.addTab("Sports");
		mScrollTabs.addTab("Life");
		mScrollTabs.addTab("Arts");
		
	}


}
