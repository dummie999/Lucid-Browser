package com.powerpoint45.lucidbrowser;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;


public class Properties extends MainActivity {
	public static int ActionbarSize=0; //used for getting the actual actionbar size + anny padding
	public static class appProp{
		public static int actionBarTransparency;
		static int actionBarColor;
		public static int primaryIntColor;
		static int urlBarColor;
		static boolean fullscreen;
		static boolean transparentNav;
		static boolean TransparentStatus;
		static boolean systemPersistent;
	}
	public static class sidebarProp{
		static int SidebarIconSize;
		static int SidebarIconPadding;
		static int SidebarSize;
		static int transparency;
		static String theme;
		static boolean showLabel;
		static int sideBarColor;
		static int sideBarTextColor;
		public static boolean swapLayout;
	}
	public static class webpageProp{
		static boolean showBackdrop;
		static boolean useDesktopView;
		static boolean clearonexit;
		static boolean closetabsonexit;
		static boolean enableimages;
		//static boolean enablejavascript; //uncomment if wanted by users
		static boolean enablecookies;
		static int     fontSize;
	}

	
	public static void update_preferences(){
		webpageProp.showBackdrop=MainActivity.mGlobalPrefs.getBoolean("showbrowserbackdrop",true);
		webpageProp.useDesktopView=MainActivity.mGlobalPrefs.getBoolean("usedesktopview",false);
		webpageProp.clearonexit=MainActivity.mGlobalPrefs.getBoolean("clearonexit",false);
		webpageProp.enableimages=MainActivity.mGlobalPrefs.getBoolean("enableimages", true);
		//webpageProp.enablejavascript=MainActivity.mGlobalPrefs.getBoolean("enablejavascript", true);
		//uncomment if wanted by users
		webpageProp.enablecookies=    MainActivity.mGlobalPrefs.getBoolean("enablecookies"  ,true);
		webpageProp.fontSize     =    MainActivity.mGlobalPrefs.getInt    ("webfontsize"    , 2);
		webpageProp.closetabsonexit = MainActivity.mGlobalPrefs.getBoolean("closetabsonexit", false);
		
		int actionBarHeight = LayoutParams.MATCH_PARENT;//fallback size
		TypedValue tv = new TypedValue();
		if (MainActivity.activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
		{
		    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,MainActivity.activity.getResources().getDisplayMetrics());
		}
		
		ActionbarSize= actionBarHeight;

		appProp.actionBarTransparency=MainActivity.mGlobalPrefs.getInt("actionbartransparency",90);
		appProp.actionBarTransparency = (255*appProp.actionBarTransparency)/100;
		appProp.fullscreen=MainActivity.mGlobalPrefs.getBoolean       ("fullscreen"           ,false);
		appProp.transparentNav=MainActivity.mGlobalPrefs.getBoolean   ("transparentnav"       ,false);
		appProp.TransparentStatus=MainActivity.mGlobalPrefs.getBoolean("transparentstatus"    ,true);
		appProp.systemPersistent=MainActivity.mGlobalPrefs.getBoolean ("systempersistent"      ,false);
		appProp.primaryIntColor=MainActivity.mGlobalPrefs.getInt      ("textcolor",Color.BLACK);
		appProp.actionBarColor=MainActivity.mGlobalPrefs.getInt       ("actionbarcolor", MainActivity.activity.getResources().getColor(R.color.urlback));
		appProp.urlBarColor=MainActivity.mGlobalPrefs.getInt          ("urlbarcolor", MainActivity.activity.getResources().getColor(R.color.urlfront));
		
		sidebarProp.SidebarIconSize=numtodp(MainActivity.mGlobalPrefs.getInt    ("sidebariconsize"  ,80));
		sidebarProp.SidebarIconPadding=numtodp(MainActivity.mGlobalPrefs.getInt ("sidebariconpadding",10));
		sidebarProp.theme=MainActivity.mGlobalPrefs.getString                   ("sidebartheme", "b");
		sidebarProp.sideBarColor=MainActivity.mGlobalPrefs.getInt               ("sidebarcolor",Color.BLACK);
        sidebarProp.sideBarTextColor=MainActivity.mGlobalPrefs.getInt           ("sidebartextcolor", Color.WHITE);
		sidebarProp.showLabel=MainActivity.mGlobalPrefs.getBoolean              ("showfavoriteslabels", true);
		sidebarProp.transparency=MainActivity.mGlobalPrefs.getInt               ("sidebartransparency" ,100);
		sidebarProp.swapLayout     =MainActivity.mGlobalPrefs.getBoolean        ("swapLayout"          ,false);
		sidebarProp.transparency= (254*sidebarProp.transparency)/100;
		if (sidebarProp.showLabel)
			sidebarProp.SidebarSize=numtodp(250);
		else
			sidebarProp.SidebarSize=sidebarProp.SidebarIconSize;
		
		
		
	}
	
	public static int numtodp(int in){
		int out =(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, in, MainActivity.activity.getResources().getDisplayMetrics());
		return out;
	}
	
	

}
