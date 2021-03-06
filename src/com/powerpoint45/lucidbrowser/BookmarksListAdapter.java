package com.powerpoint45.lucidbrowser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BookmarksListAdapter extends BaseAdapter {
	URL url;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return MainActivity.mPrefs.getInt("numbookmarkedpages", 0);
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		RelativeLayout RL = (RelativeLayout) MainActivity.inflater.inflate(R.layout.bookmark_item, null);
		((TextView) RL.findViewById(R.id.bookmark_title)).setText(MainActivity.mPrefs.getString("bookmarktitle"+arg0, "null"));
		if (MainActivity.mPrefs.getString("bookmark"+arg0, "null").compareTo(MainActivity.assetHomePage)==0)
			((TextView) RL.findViewById(R.id.bookmark_url_title)).setText("about:home");
		else
			((TextView) RL.findViewById(R.id.bookmark_url_title)).setText(MainActivity.mPrefs.getString("bookmark"+arg0, "null"));
		
		try {
			url = new URL(MainActivity.mPrefs.getString("bookmark"+arg0, ""));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (url!=null && url.getHost().compareTo("")!=0 && url.getPath().compareTo(MainActivity.assetHomePage)!=0 && new File(BookmarksActivity.activity.getApplicationInfo().dataDir+"/icons/"+ url.getHost()).exists())
			((ImageView) RL.findViewById(R.id.bookmark_icon)).setImageBitmap(BitmapFactory.decodeFile(BookmarksActivity.activity.getApplicationInfo().dataDir+"/icons/"+ url.getHost()));
		
		RL.setTag(MainActivity.mPrefs.getString("bookmark"+arg0, "www.google.com"));
		return RL;
	}

}
