scrollTabs
==========

A scroll tabs that you can use easily</br>
<img src="https://cloud.githubusercontent.com/assets/8369164/5586317/beee0bf8-9104-11e4-9053-5419d52babf6.png"/></br>
Simple Example
Download the scrollTabs and import into eclipse.add to you progect as an libary.

1.Activity
```java
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
```
2.XMl

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:shiji="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#fff"
     >
	<com.shiji.view.ScrollTabs
	    android:id="@+id/main_scrollTabs"
	    android:layout_width="match_parent"
	    android:layout_height="40dp"
	    android:background="#FCFCFC"
	    shiji:textColor="#333"
	    shiji:selectedTextColor="#FF0000"
	    shiji:tabMinWidth="90dp"
	    />
	<View 
	    android:layout_width="match_parent"
	    android:layout_height="1px"
	    android:background="#a0a0a0"
	    />
	    
</LinearLayout>
```

