package com.powerpoint45.lucidbrowser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import com.powerpoint45.lucidbrowser.R;


public class BrowserHandler extends Activity{

public static void dlImage(URL url){
	final URL rl = url;
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			String finalFileName = "";
			File finalFile = null;
			try{
				InputStream input = rl.openStream(); 
				File storagePath = new File(Environment.getExternalStorageDirectory().toString()+"/Download/");
				storagePath.mkdir();
				try {     
				    String s = rl.toString();
				    finalFileName = s.substring(s.lastIndexOf('/')+1).trim();
				    if (!finalFileName.contains("."))
				    	finalFileName = finalFileName+".png";
				    finalFile = new File(storagePath+"/"+finalFileName);
				    OutputStream output = new FileOutputStream (finalFile.getAbsolutePath()); 
				    try {         
				        byte[] buffer = new byte[1024];         
				        int bytesRead = 0;         
				        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
				                output.write(buffer, 0, bytesRead);         
				        }     
				    }   
				    finally {  
				        output.close(); 
				    } 
				} 
			
				finally {     
					Message msg = new Message();
 	         	    msg.what = 1;
 	         	    msg.obj = MainActivity.activity.getResources().getString(R.string.savedimage)+" "+storagePath.toString()+"/"+finalFileName;
 	                MainActivity.messageHandler.sendMessage(msg);
 	                addImageGallery(finalFile);
				    input.close(); 
				}
				}catch(Exception e){
					Message msg = new Message();
 	         	    msg.what = 1;
 	         	    msg.obj = MainActivity.activity.getResources().getString(R.string.failed);
 	                MainActivity.messageHandler.sendMessage(msg);
				}
		}
	}).start();
}
		static void addImageGallery( File file ) {
		    ContentValues values = new ContentValues();
		    values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
		    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg"); // setar isso
		    MainActivity.activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		}

    }
